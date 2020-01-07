package com.pchudzik.blog.example.structuredlogging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.message.StringMapMessage;
import org.apache.logging.log4j.message.StructuredDataMessage;

import java.util.Map;
import java.util.UUID;

public class App {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        log
                .atInfo()
                .log(new StringMapMessage(Map.of("user", "john Smith 123", "request", "asj8272u1jk")));
        log.info(
                new StructuredDataMessage(
                        "sany",
                        "Some message string",
                        "ala ma kota",
                        Map.of("a", "b", "c", "d")),
                new NullPointerException());
    }
}
