package io.spring.batch.helloworldbatch.job;

import io.spring.batch.helloworldbatch.domain.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DelimitedFileJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public DelimitedFileJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> customerFileReader(
            @Value("#{jobParameters['customerFile']}") Resource resource
    ) {

        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerFileReader")
                .resource(resource)
                .delimited()
                .names("firstName", "middleInitial", "lastName", "address", "city", "state", "zipCode")
                .targetType(Customer.class)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<Customer> customerItemWriter(
            @Value("#{jobParameters['outputFile']}") Resource outputFile
    ) {
        return new FlatFileItemWriterBuilder<Customer>()
                .name("customerItemWriter")
                .resource(outputFile)
                .delimited()
                .delimiter(";")
                .names("zipCode", "state", "city", "address", "lastName", "firstName")
                .build();
    }

    @Bean
    public Step formatStep() {
        return this.stepBuilderFactory.get("formatStep")
                .<Customer, Customer>chunk(10)
                .reader(customerFileReader(null))
                .writer(customerItemWriter(null))
                .build();
    }

    @Bean
    public Job formatJob() {
        return this.jobBuilderFactory.get("formatJob")
                .start(formatStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
