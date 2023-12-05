package org.irrigation.dataprocessor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.irrigation.schema.ParcelData;
import org.irrigation.schema.SystemControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataProcessor {
    private static final String CONTROLLER_TOPIC = "controller";
    private static final Map<String, SystemControl> irrigationStateMap = new HashMap<>();

    public static void process(StreamsBuilder builder) {
        KStream<String, String> waterLevels = builder.stream("water-levels", Consumed.with(Serdes.String(), Serdes.String()));
        KStream<String, ParcelData> parcelData = builder.stream("parcel-data");

        ArrayList<String> availableReservoirs = new ArrayList<>();

        waterLevels
                .filter((k, v) -> k.endsWith("bottom"))
                .foreach((k, v) -> {
                    if (!availableReservoirs.contains(k) && v.endsWith("true")) {
                        availableReservoirs.add(k);
                    } else if (availableReservoirs.contains(k) && v.endsWith("false")) {
                        availableReservoirs.remove(k);
                    }
                });

        /*
        Here we create a new stream, which we use to put data on the "controller" topic
        It will receive all records from the water-levels topic and send them for processing to the refillLoop function
        If there is no action to be taken (no need to start or stop the refilling pump), the refillLoop will return null
        In which case, we don't do anything, we will check the next record
        If we have to refill a reservoir, we will send to the controller topic a record to start the refill pump, and the valve number for the corresponding reservoir.
        */

        KStream<String, SystemControl> refillStream = waterLevels.map((key, value) -> KeyValue.pair("refill", reservoirRefillingLoopControl(key, value)));
        refillStream.filter((key, value) -> value != null).to(CONTROLLER_TOPIC);

        KStream<String, SystemControl> irrigationStream = parcelData.map((key, value) -> KeyValue.pair("irrigate", irrigationControl(key, value, availableReservoirs)));
        irrigationStream.filter((key, value) -> value != null).to(CONTROLLER_TOPIC);
    }

    private static SystemControl reservoirRefillingLoopControl(String key, String value) {
        SystemControl systemControl = new SystemControl();

        int reservoirNr = getReservoirNumber(key);
        String sensorPosition = getSensorPosition(key);

        boolean isWaterDetected = isWaterDetected(value);

        if (sensorPosition.equals("bottom") && !isWaterDetected) {// Pumps started for reservoir number X
            systemControl.setAct(true);
            systemControl.setRV(reservoirNr);
        } else if (sensorPosition.equals("top") && isWaterDetected) {
            // Pumps stopped for reservoir number X
            systemControl.setAct(false);
            systemControl.setRV(reservoirNr);
        } else {
            return null;
        }

        return systemControl;
    }

    private static SystemControl irrigationControl(String key, ParcelData value, ArrayList<String> availableReservoirs) {
        SystemControl systemControl = new SystemControl();
        int parcelNr = getParcelNumber(key);

        if (value.getT() > 30 && value.getH() < 15) {
            System.out.println("1st if");

            int reservoirKey = getReservoirNumber(availableReservoirs.get(0));
            systemControl.setAct(true);
            systemControl.setRV(reservoirKey);
            systemControl.setPV(parcelNr);
            irrigationStateMap.put(key, systemControl);

        } else if (value.getH() > 18 && irrigationStateMap.containsKey(key)) {
            System.out.println("3rd if");
            systemControl.setAct(false);
            systemControl.setRV(irrigationStateMap.get(key).getRV());
            systemControl.setPV(irrigationStateMap.get(key).getPV());
            irrigationStateMap.remove(key);
        } else {
            return null;
        }

        return systemControl;
    }

    private static int getReservoirNumber(String key) {
        String[] keyParts = key.split("_");
        return Integer.parseInt(keyParts[1]);
    }

    private static String getSensorPosition(String key) {
        String[] keyParts = key.split("_");
        return keyParts[2];
    }

    private static Boolean isWaterDetected(String value) {
        String[] valueParts = value.split(" ");
        return Boolean.parseBoolean(valueParts[1]);
    }

    private static int getParcelNumber(String key) {
        String[] keyParts = key.split("_");
        return Integer.parseInt(keyParts[1]);
    }
}
