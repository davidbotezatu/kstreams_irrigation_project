package org.irrigation;

import org.irrigation.config.ProducerConfigFactory;
import org.irrigation.producers.ProducerFactory;
import org.irrigation.utils.TestDataCreation;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final String BOOTSTRAP_SERVERS = "localhost:19092,localhost:29092,localhost:39092";
    private static final String TOPIC = "water-levels";
    private static final long SLEEP_DURATION = 1000; // Sleep duration between messages - for testing purposes
    private static final Path DATA_DIRECTORY = Paths.get(System.getProperty("user.dir"), "test_data");

    public static void main(String[] args) {
        // Create test data
        TestDataCreation.createData(100);

        //type 1 for reservoir producer, type 2 for the parcel producers
        Properties producerProps = ProducerConfigFactory.createProducerConfig(BOOTSTRAP_SERVERS);

        // Start one producer for each reservoir
        for (int i = 1; i <= 7; i++) {
            Path topSensorFilePath = DATA_DIRECTORY.resolve("reservoir_" + i + "_top.txt");
            Path bottomSensorFilePath = DATA_DIRECTORY.resolve("reservoir_" + i + "_bottom.txt");

            // Start producer for both sensors
            new Thread(new ProducerFactory(
                    TOPIC,
                    topSensorFilePath,
                    bottomSensorFilePath,
                    producerProps,
                    SLEEP_DURATION
            )).start();
        }
    }
}