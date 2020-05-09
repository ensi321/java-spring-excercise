package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        final String db_url = "jdbc:sqlite:" + System.getProperty("java.io.tmpdir") + "test.db";

        SpringApplication.run(MainApplication.class, args);

    }
}