package io.spring.batch.helloworldbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class NoRunJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job3() {
        return this.jobBuilderFactory.get("job3")
                .start(step3())
                .build();
    }

    @Bean
    public Step step3() {
        return this.stepBuilderFactory.get("step3")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("step3 ran!");
                    return RepeatStatus.FINISHED;
                })).build();
    }

    @Bean
    public Job job4() {
        return this.jobBuilderFactory.get("job4")
                .start(step4())
                .build();
    }

    @Bean
    public Step step4() {
        return this.stepBuilderFactory.get("step4")
                .tasklet(((stepContribution, chunkContext) -> {
                    System.out.println("step4 ran!");
                    return RepeatStatus.FINISHED;
                })).build();
    }
}
