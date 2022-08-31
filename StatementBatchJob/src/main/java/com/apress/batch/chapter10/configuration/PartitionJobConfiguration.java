package com.apress.batch.chapter10.configuration;

import com.apress.batch.chapter10.domain.Transaction2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;

@Configuration
public class PartitionJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job partitionedJob() throws Exception {
        return this.jobBuilderFactory.get("partitionedJob")
                .start(partitionedMaster())
                .build();
    }

    @Bean
    public Step partitionedMaster() {
        return this.stepBuilderFactory.get("step1")
                .partitioner(step1().getName(), partitioner(null))
                .partitionHandler(partitionHandler())
                .build();
    }

    @Bean
    public TaskExecutorPartitionHandler partitionHandler() {
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();

        partitionHandler.setStep(step1());
        partitionHandler.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return partitionHandler;
    }

    @Bean
    @StepScope
    public MultiResourcePartitioner partitioner(
            @Value("#{jobParameters['inputFiles']}") Resource[] resources
    ) {

        MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
        partitioner.setKeyName("file");
        partitioner.setResources(resources);

        return partitioner;
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .<Transaction2, Transaction2>chunk(100)
                .reader(fileTransactionReader(null))
                .writer(writer(null))
                .build();
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Transaction2> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Transaction2>()
                .dataSource(dataSource)
                .beanMapped()
                .sql("insert into transaction2 (account, amount, timestamp) values (:account, :amount, :timestamp)")
                .build();
    }


    @Bean
    @StepScope
    public FlatFileItemReader<Transaction2> fileTransactionReader(
            @Value("#{stepExecutionContext['file']}") Resource resource
    ) {
        return new FlatFileItemReaderBuilder<Transaction2>()
                .name("flatFileTransactionReader")
                .resource(resource)
                .delimited()
                .names("account", "amount", "timestamp")
                .fieldSetMapper(fieldSet -> {
                    Transaction2 transaction = new Transaction2();
                    transaction.setAccount(fieldSet.readString("account"));
                    transaction.setAmount(fieldSet.readBigDecimal("amount"));
                    transaction.setTimestamp(fieldSet.readDate("timestamp", "yyyy-MM-dd HH:mm:ss"));

                    return transaction;
                })
                .build();
    }

}
