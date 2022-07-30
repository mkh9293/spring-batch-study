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
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class XmlFileJob {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    public XmlFileJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
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
    public StaxEventItemWriter<Customer> customerItemWriter(
            @Value("#{jobParameters['outputFile']}") Resource outputFile
    ) {

        Map<String, Class> aliases = new HashMap<>();
        aliases.put("customer", Customer.class);

        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAliases(aliases);

        return new StaxEventItemWriterBuilder<Customer>()
                .name("customerItemWriter")
                .resource(outputFile)
                .marshaller(marshaller)
                .rootTagName("customers")
                .build();
    }

    @Bean
    public Step formatStep() {
        return this.stepBuilderFactory.get("xmlFormatStep")
                .<Customer, Customer>chunk(10)
                .reader(customerFileReader(null))
                .writer(customerItemWriter(null))
                .build();
    }

    @Bean
    public Job formatJob() {
        return this.jobBuilderFactory.get("xmlFormatJob")
                .start(formatStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
