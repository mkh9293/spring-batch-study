package io.spring.batch.helloworldbatch.job;

import io.spring.batch.helloworldbatch.domain.Customer;
import io.spring.batch.helloworldbatch.mapper.TransactionFieldSetMapper;
import io.spring.batch.helloworldbatch.reader.CustomerFileReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

@EnableBatchProcessing
@SpringBootApplication
public class MultiLineJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("multiLineJob")
                .start(multiLineStep())
                .build();
    }

    @Bean
    public Step multiLineStep() {
        return this.stepBuilderFactory.get("multiLineStep")
                .<Customer, Customer>chunk(10)
//                .reader(customerItemReader(null))
                .reader(new CustomerFileReader(customerItemReader(null)))
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemWriter itemWriter() {
        return (items) -> items.forEach(System.out::println);
    }

    @Bean
    @StepScope
    public FlatFileItemReader customerItemReader(
            @Value("#{jobParameters['customerFile']}") Resource inputFile
    ) {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReader")
                .lineMapper(lineTokenizer())
                .resource(inputFile)
                .build();
    }

    @Bean
    public PatternMatchingCompositeLineMapper lineTokenizer() {
        Map<String, LineTokenizer> lineTokenizerMap = new HashMap<>(2);

        lineTokenizerMap.put("CUST*", customerLineTokenizer());
        lineTokenizerMap.put("TRANS*", transactionLineTokenizer());

        Map<String, FieldSetMapper> fieldSetMapperMap = new HashMap<>(2);

        BeanWrapperFieldSetMapper<Customer> customerFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        customerFieldSetMapper.setTargetType(Customer.class);

        fieldSetMapperMap.put("CUST*", customerFieldSetMapper);
        fieldSetMapperMap.put("TRANS*", new TransactionFieldSetMapper());

        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();

        lineMapper.setTokenizers(lineTokenizerMap);
        lineMapper.setFieldSetMappers(fieldSetMapperMap);

        return lineMapper;
    }

    @Bean
    public DelimitedLineTokenizer transactionLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setNames("prefix", "accountNumber", "transactionDate", "amount");

        return lineTokenizer;
    }

    @Bean
    public DelimitedLineTokenizer customerLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setNames("firstName", "middleInitial", "lastName", "address", "city", "state","zipCode");
        lineTokenizer.setIncludedFields(1, 2, 3, 4, 5, 6, 7);

        return lineTokenizer;
    }



}
