package io.spring.batch.helloworldbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@EnableBatchProcessing
//@SpringBootApplication
public class FlowJob {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job conditionalStepLogicJob() {
//        return this.jobBuilderFactory.get("conditionalStepLogicJob")
//
//                // 잡 빌더내에서 flow 를 직접 호출하면, jobRepository 내에서는 단순히 잡에서 스텝을 호출하는것과 동일하게 보인다.
//                // 반면, 스텝으로 한번 래핑하는 경우 추가적인 정보가 더 담기게 되어 모니터링하기에 좋다.
//
//                // 잡 빌더내에서 flow 를 직접 호출하지 않고, 스텝으로 래핑한다.
//                .start(initializeBatch())
//                .next(runBatch())
//                .build();
//
//        // 잡 빌더내에서 flow 를 직접 호출한다.
////                .start(preprocessingFlow())
////                .next(runBatch())
////                .end()
////                .build();
//    }
//
//    @Bean
//    public Step initializeBatch() {
//        return this.stepBuilderFactory.get("initializeBatch")
//                .flow(preprocessingFlow())
//                .build();
//    }
//
//    @Bean
//    public Step runBatch() {
//        return this.stepBuilderFactory.get("runBatch")
//                .tasklet(runBatchTasklet())
//                .build();
//    }
//
//    @Bean
//    public Tasklet runBatchTasklet() {
//        return ((stepContribution, chunkContext) -> {
//            System.out.println("The batch has been run");
//            return RepeatStatus.FINISHED;
//        });
//    }
//
//    @Bean
//    public Flow preprocessingFlow() {
//        return new FlowBuilder<Flow>("preprocessingFlow").start(loadFileStep())
//                .next(loadCustomerStep())
//                .next(updateStartStep())
//                .build();
//    }
//
//    @Bean
//    public Step loadFileStep() {
//        return this.stepBuilderFactory.get("loadFileStep")
//                .tasklet(loadStockFile())
//                .build();
//    }
//
//    @Bean
//    public Tasklet loadStockFile() {
//        return ((stepContribution, chunkContext) -> {
//            System.out.println("The stock file has been loaded");
//            return RepeatStatus.FINISHED;
//        });
//    }
//
//    @Bean
//    public Step loadCustomerStep() {
//        return this.stepBuilderFactory.get("loadCustomerStep")
//                .tasklet(loadCustomerFile())
//                .build();
//    }
//
//    @Bean
//    public Tasklet loadCustomerFile() {
//        return ((stepContribution, chunkContext) -> {
//            System.out.println("The customer file has been loaded");
//            return RepeatStatus.FINISHED;
//        });
//    }
//
//    @Bean
//    public Step updateStartStep() {
//        return this.stepBuilderFactory.get("updateStartStep")
//                .tasklet(updateStart())
//                .build();
//    }
//
//    @Bean
//    public Tasklet updateStart() {
//        return ((stepContribution, chunkContext) -> {
//            System.out.println("The start has been updated");
//            return RepeatStatus.FINISHED;
//        });
//    }
}
