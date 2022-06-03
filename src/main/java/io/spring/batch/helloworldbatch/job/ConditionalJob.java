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
                .on("FAILED").stopAndRestart(successStep()) // 해당 step 을 동일한 파라미터로 잡 재실행 가능 (첫 스텝인 fisrtStep() 이 아닌 successStep() 부터 실행함.)
                .from(firstStep()).on("*").to(successStep())

                // decider 를 사용하여 내부로직에 따라 스텝을 선택 가능
//                .next(decider())
//                .from(decider())
//                .on("FAILED").to(failureStep())
//                .from(decider())
//                .on("*").to(successStep())

                // ExitStatus 에 따라 step 선택
//                .start(firstStep())
//                .on("FAILED").to(failureStep())
//                .from(firstStep()).on("*").to(successStep())

//                .on("FAILED").end() // end() 로 끝나는 경우 ExitStatus가 실패여도 db 에는 Complete 가 되어 동일한 파라미터로 잡 실행 불가능.
//                .on("FAILED").fail() // end() 와 다르게 실패로 끝나서 다시 동일한 파라미터로 잡 실행가능
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
