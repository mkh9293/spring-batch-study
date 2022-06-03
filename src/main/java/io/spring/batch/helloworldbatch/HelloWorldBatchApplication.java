package io.spring.batch.helloworldbatch;

import io.spring.batch.helloworldbatch.job.ChunkJob;
import io.spring.batch.helloworldbatch.job.ConditionalJob;
import org.springframework.boot.SpringApplication;

public class HelloWorldBatchApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TaskletJob.class, args);
//        SpringApplication.run(ChunkJob.class, args);
        SpringApplication.run(ConditionalJob.class, args);
    }

}
