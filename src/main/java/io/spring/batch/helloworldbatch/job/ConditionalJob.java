package io.spring.batch.helloworldbatch.job;

import io.spring.batch.helloworldbatch.decider.RandomDecider;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class ConditionalJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("conditionalJob")
                .start(firstStep())
                .next(decider())
                .from(decider())
                .on("FAILED").to(failureStep())
                .from(decider())
                .on("*").to(successStep())

//                .start(firstStep())
//                .on("FAILED").to(failureStep())
//                .from(firstStep()).on("*").to(successStep())
                .end()
                .build();
    }

    @Bean
    public Step successStep() {
        return this.stepBuilderFactory.get("successStep")
                .tasklet(successTasklet())
                .build();
    }

    @Bean
    public Tasklet successTasklet() {
        return ((stepContribution, chunkContext) -> {
            System.out.println("Success");
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public Step firstStep() {
        return this.stepBuilderFactory.get("firstStep")
                .tasklet(passTasklet())
                .build();
    }

    @Bean
    public Tasklet passTasklet() {
        return ((stepContribution, chunkContext) -> {
           return RepeatStatus.FINISHED;
//           throw new RuntimeException("This is a failure");
        });
    }

    @Bean
    public Step failureStep() {
        return this.stepBuilderFactory.get("failureStep")
                .tasklet(failTasklet())
                .build();
    }

    @Bean
    public Tasklet failTasklet() {
        return ((stepContribution, chunkContext) -> {
            System.out.println("Failure!");
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public JobExecutionDecider decider() {
        System.out.println("decider!!");
        return new RandomDecider();
    }
}
