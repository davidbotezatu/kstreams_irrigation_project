package org.irrigation;

import org.irrigation.config.ProducerConfigFactory;
import org.irrigation.producers.ProducerFactory;
import org.irrigation.utils.TestDataCreation;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {
    private static final String BOOTSTRAP_SERVERS = "localhost:19092,localhost:29092,localhost:39092";
    private static final String PARCEL_TOPIC = "parcel-data";
    private static final long SLEEP_DURATION = 1000; // Sleep duration between messages - for testing purposes
    private static final Path PARCEL_DATA_DIR = Paths.get(System.getProperty("user.dir"), "parcels_test_data");

    public static void main(String[] args) {
        // Create test data
        TestDataCreation.createData(100);

        Properties parcelProps = ProducerConfigFactory.createProducerConfig(BOOTSTRAP_SERVERS);

        //Start one producer for each parcel
        for (int i = 1; i <= 3; i++) {
            Path parcelData = PARCEL_DATA_DIR.resolve("parcel_" + i + ".txt");

            new Thread(new ProducerFactory(PARCEL_TOPIC, parcelData, parcelProps, SLEEP_DURATION)).start();
        }
    }
}