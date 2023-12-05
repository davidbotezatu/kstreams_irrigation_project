package org.irrigation.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.irrigation.schema.ParcelData;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProducerFactory implements Runnable {
    private final String topic;
    private final Path parcelSensorsFilePath;
    private final Properties producerProps;
    private final long sleepDuration;

    public ProducerFactory(String topic, Path parcelDataFilePath, Properties producerProps, long sleepDuration) {
        this.topic = topic;
        this.parcelSensorsFilePath = parcelDataFilePath;
        this.producerProps = producerProps;
        this.sleepDuration = sleepDuration;
    }

    @Override
    public void run() {
        try (BufferedReader reader = Files.newBufferedReader(parcelSensorsFilePath)) {
            Producer<String, ParcelData> producer = new KafkaProducer<>(producerProps);

            String line;
            while (((line = reader.readLine()) != null)) {
                int temp = extractSensorValue(line);
                int humid = extractSensorValue(reader.readLine());

                sendSensorData(producer, parcelSensorsFilePath.getFileName().toString().replace(".txt", ""), new ParcelData(temp, humid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer extractSensorValue(String line) {
        Pattern pattern = Pattern.compile("[TH](\\d+)");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        return null;
    }

    private void sendSensorData(Producer<String, ParcelData> producer, String key, ParcelData value) throws InterruptedException {
        ProducerRecord<String, ParcelData> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
        Thread.sleep(sleepDuration);
    }
}
