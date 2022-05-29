package io.spring.batch.helloworldbatch;

import io.spring.batch.helloworldbatch.validator.CompositeParameterValidator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldBatchApplication.class, args);
    }

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .tasklet(helloWorldTasklet(null, null))
//                .tasklet(helloWorldTasklet())
                .build();

//                .tasklet(((stepContribution, chunkContext) -> {
//                    System.out.println("Hello, world!");
//                    return RepeatStatus.FINISHED;
//                })).build();

//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("Hello, World!");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
    }

    // 실제 step(tasklet) 이 호출되기 전, 앱 로딩 시점에 미리 호출된다.
//    @Bean
//    public Tasklet helloWorldTasklet() {
//        System.out.println("test!!!");
//
//        return ((stepContribution, chunkContext) -> {
//           String name = (String) chunkContext.getStepContext()
//                   .getJobParameters()
//                   .get("name");
//
//           System.out.println(String.format("Hello, %s!", name));
//           return RepeatStatus.FINISHED;
//        });
//    }

    // 늦은 바인딩 사용. 실제 job 이 해당 tasklet 을 호출할 때까지 지연 후 실행된다.
    @StepScope
    @Bean
    public Tasklet helloWorldTasklet(
            @Value("#{jobParameters['fileName']}") String fileName,
            @Value("#{jobParameters['name']}") String name
    ) {

        System.out.println("test!!!");

        return ((stepContribution, chunkContext) -> {
            System.out.printf("Hello, %s!%n", name);
            System.out.printf("fileName = %s!%n", fileName);
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public Job job(){
        return this.jobBuilderFactory.get("basicJob")
                .start(step1())
                .validator(new CompositeParameterValidator().compositeValidator())
                .build();
    }


}
