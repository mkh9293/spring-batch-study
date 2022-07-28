package com.example.jpabatch;

import com.example.jpabatch.job.JpaJob;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class JpaBatchApplication {

    public static void main(String[] args) {
//        SpringApplication.run(JpaBatchApplication.class, args);
//        SpringApplication.run(JpaJob.class, args);
    }

}
