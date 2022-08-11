package com.apress.batch.chapter10;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class StatementBatchJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatementBatchJobApplication.class, args);
    }

}
