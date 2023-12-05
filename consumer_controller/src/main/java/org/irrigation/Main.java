package org.irrigation;

import org.irrigation.consumers.ConsumerFactory;

public class Main {
    private static final String CONTROLLER_TOPIC = "controller";
    private static final String BOOTSTRAP_SERVERS = "localhost:19092,localhost:29092,localhost:39092";

    public static void main(String[] args) {
        new Thread(new ConsumerFactory(CONTROLLER_TOPIC, BOOTSTRAP_SERVERS, "refill-consumer", "refill")).start();
        new Thread(new ConsumerFactory(CONTROLLER_TOPIC, BOOTSTRAP_SERVERS, "irrigation-consumer", "irrigate")).start();
    }
}