package io.spring.batch.helloworldbatch.job;

import io.spring.batch.helloworldbatch.policy.RandomChunkSizePolicy;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.policy.CompositeCompletionPolicy;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.policy.TimeoutTerminationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EnableBatchProcessing
@SpringBootApplication
public class ChunkJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkBasedJob() {
        return this.jobBuilderFactory.get("chunkBasedJob")
                .start(chunkStep())
                .build();
    }

    @Bean
    public Step chunkStep() {
//        return this.stepBuilderFactory.get("chunkStep")
        return this.stepBuilderFactory.get("randomChunkStep3")
//                .<String, String>chunk(completionPolicy())
                .<String, String>chunk(randomCompletionPolicy())
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ListItemReader<String> itemReader() {
//        List<String> items = new ArrayList<>(100000);
        List<String> items = new ArrayList<>(100);

//        for(int i=0; i<100000; i++) {
        for(int i=0; i<100; i++) {
            items.add(UUID.randomUUID().toString());
        }

        return new ListItemReader<>(items);
    }

    @Bean
    public ItemWriter<String> itemWriter() {
        return items -> {
            for(String item: items) {
                System.out.println(">> current item = " + item);
            }
        };
    }

    // 청크가 완료됬다고 판단하는 여러가지 설정한 정책 중에 하나라도 끝나면 해당 청크는 끝났다고 판단한다.
    @Bean
    public CompletionPolicy completionPolicy() {
        CompositeCompletionPolicy policy = new CompositeCompletionPolicy();

        // 3초가 지났거나 1000개의 청크가 끝났거나 둘중 하나라도 조건을 충족하는 경우 현재 진행중이었던 청크는 끝났다고 판단한다.
        policy.setPolicies(
                new CompletionPolicy[] {
                        new TimeoutTerminationPolicy(3),
                        new SimpleCompletionPolicy(1000)
                }
        );

        return policy;
    }

    @Bean
    public CompletionPolicy randomCompletionPolicy() {
        return new RandomChunkSizePolicy();
    }
}
