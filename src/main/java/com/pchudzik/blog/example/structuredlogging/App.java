package com.pchudzik.blog.example.structuredlogging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.message.StringMapMessage;
import org.apache.logging.log4j.message.StructuredDataMessage;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.Arrays.asList;

public class App {
    private static final Logger log = LogManager.getLogger();
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        log
                .atInfo()
                .log(new StringMapMessage(Map.of("user", "john Smith 123", "request", "asj8272u1jk")));

        while (true) {
            log.info(App::randomMessage);
            Thread.sleep(1000);
        }
    }

    private static Message randomMessage() {
        List<Message> allMessages = asList(
                new StructuredDataMessage(
                        "performance_trace",
                        "Load categories for product",
                        "",
                        Map.of(
                                "executionTime", randomValue(100, 20),
                                "product", randomValue(10),
                                "user", "user" + randomValue(10))),
                new StructuredDataMessage(
                        "action_trace",
                        "User buys things",
                        "",
                        Map.of(
                                "executionTime", randomValue(100, 20),
                                "products", "" + randomValue(1000) + "," + randomValue(1000),
                                "user", "user" + randomValue(10),
                                "basket_time", randomValue(10_000, 100_000),
                                "basket_size", randomValue(10))),
                new SimpleMessage("checking things")
        );

        return allMessages.get(random.nextInt(allMessages.size()));
    }

    private static String randomValue(int factor) {
        return randomValue(0, factor);
    }

    private static String randomValue(int base, int factor) {
        return "" + (base + random.nextInt(factor));
    }
}
