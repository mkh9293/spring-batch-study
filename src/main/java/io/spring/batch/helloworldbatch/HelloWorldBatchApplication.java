package io.spring.batch.helloworldbatch;

import io.spring.batch.helloworldbatch.job.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class HelloWorldBatchApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TaskletJob.class, args);
//        SpringApplication.run(ChunkJob.class, args);
//        SpringApplication.run(ConditionalJob.class, args);
//        SpringApplication.run(FlowJob.class, args);
//        SpringApplication.run(NoRunJob.class, args);
//        SpringApplication.run(RestApplication.class, args);
//        SpringApplication.run(QuartzJobConfiguration.class, args);
//        SpringApplication.run(FixedWidthJob.class, args);
//        SpringApplication.run(MultiLineJob.class, args);
//        SpringApplication.run(XmlJob.class, args);
//        SpringApplication.run(JdbcCursorJob.class, args);
//        SpringApplication.run(JdbcPagingJob.class, args);
//        SpringApplication.run(HibernateCursorJob.class, args);
//        SpringApplication.run(JpaJob.class, args);
//        SpringApplication.run(StoredProcedureJob.class, args);
        SpringApplication.run(SpringDataRepositoryJob.class, args);
    }

}
