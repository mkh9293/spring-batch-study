package io.spring.batch.helloworldbatch;

import io.spring.batch.helloworldbatch.job.*;
import org.springframework.boot.SpringApplication;

public class HelloWorldBatchApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TaskletJob.class, args);
//        SpringApplication.run(ChunkJob.class, args);
//        SpringApplication.run(ConditionalJob.class, args);
//        SpringApplication.run(FlowJob.class, args);
//        SpringApplication.run(NoRunJob.class, args);
        SpringApplication.run(RestApplication.class, args);
    }

}
