package org.irrigation;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.irrigation.config.StreamsConfigFactory;
import org.irrigation.dataprocessor.DataProcessor;

public class Main {
    private static final String APP_NAME = "irrigation-app";
    private static final String BOOTSTRAP_SERVERS = "localhost:19092,localhost:29092,localhost:39092";
    private static final String SCHEMA_REGISTRY_URL = "http://localhost:8082";

    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();

        DataProcessor.process(builder);

        KafkaStreams streams = new KafkaStreams(builder.build(), StreamsConfigFactory.createKStreamsConfig(APP_NAME, BOOTSTRAP_SERVERS, SCHEMA_REGISTRY_URL));
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}