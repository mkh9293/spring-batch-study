package io.spring.batch.helloworldbatch.job;

import io.spring.batch.helloworldbatch.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.HibernateItemWriter;
import org.springframework.batch.item.database.builder.HibernateItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.persistence.EntityManagerFactory;

//@Configuration
public class HibernateImportJob {

//    private JobBuilderFactory jobBuilderFactory;
//    private StepBuilderFactory stepBuilderFactory;
//
//    public HibernateImportJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Customer> customerFileReader(
//            @Value("#{jobParameters['customerFile']}") Resource resource
//    ) {
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("customerFileReader")
//                .resource(resource)
//                .delimited()
//                .names("firstName", "middleInitial", "lastName", "address", "city", "state", "zipCode")
//                .targetType(Customer.class)
//                .build();
//    }
//
//    @Bean
//    public HibernateItemWriter<Customer> hibernateItemWriter(EntityManagerFactory entityManagerFactory) {
//        return new HibernateItemWriterBuilder<Customer>()
//                .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
//                .build();
//    }
//
//    @Bean
//    public Step hibernateFormatStep() throws Exception {
//        return this.stepBuilderFactory.get("jdbcFormatStep")
//                .<Customer, Customer>chunk(10)
//                .reader(customerFileReader(null))
//                .writer(hibernateItemWriter(null))
//                .build();
//    }
//
//    @Bean
//    public Job hibernateFormatJob() throws Exception {
//        return this.jobBuilderFactory.get("hibernateFormatJob")
//                .start(hibernateFormatStep())
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }
}
