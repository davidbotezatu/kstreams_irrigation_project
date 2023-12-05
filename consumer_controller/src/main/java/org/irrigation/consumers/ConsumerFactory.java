package org.irrigation.consumers;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.irrigation.config.ConsumerConfigFactory;
import org.irrigation.schema.SystemControl;

import java.time.Duration;
import java.util.Collections;

public class ConsumerFactory implements Runnable {
    private final String CONTROLLER_TOPIC;
    private final String BOOTSTRAP_SERVERS;
    private final String GROUP_ID;
    private final String CONSUMER_TYPE;

    public ConsumerFactory(String controllerTopic, String bootstrapServers, String groupId, String consumerType) {
        this.CONTROLLER_TOPIC = controllerTopic;
        this.BOOTSTRAP_SERVERS = bootstrapServers;
        this.GROUP_ID = groupId;
        this.CONSUMER_TYPE = consumerType;
    }

    @Override
    public void run() {
        try(Consumer<String, SystemControl> consumer = new KafkaConsumer<>(ConsumerConfigFactory.createConsumerConfig(BOOTSTRAP_SERVERS, GROUP_ID))) {
            consumer.subscribe(Collections.singletonList(CONTROLLER_TOPIC));

            while (true) {
                ConsumerRecords<String, SystemControl> records = consumer.poll(Duration.ofMillis(100));

                records.forEach(record -> {
                    if (record.key().equals(CONSUMER_TYPE)) {
                        systemController(record.value(), CONSUMER_TYPE);
                    }
                });
            }
        }
    }

    private void systemController(SystemControl val, String consumerType) {
        if (consumerType.equals("refill")) {
            if (val.getAct()) {
                System.out.println("Started " + CONSUMER_TYPE + " pump, and opened valve for reservoir " + val.getRV());
            } else {
                System.out.println("Stopped " + CONSUMER_TYPE + " pump, and closed valve for reservoir " + val.getRV());
            }
        } else {
            if (val.getAct()) {
                System.out.println("Started " + CONSUMER_TYPE + " pump, opened valve from reservoir " + val.getRV() + " and opened valve for parcel " + val.getPV());
            } else {
                System.out.println("Stopped " + CONSUMER_TYPE + " pump, closed valve from reservoir " + val.getRV() + " and closed valve for parcel " + val.getPV());
            }
        }
    }
}
