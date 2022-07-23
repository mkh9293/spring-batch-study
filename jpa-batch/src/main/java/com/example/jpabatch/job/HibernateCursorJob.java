package com.example.jpabatch.job;

import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.HibernatePagingItemReader;
import org.springframework.batch.item.database.builder.HibernatePagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;

//@EnableBatchProcessing
//@SpringBootApplication
public class HibernateCursorJob {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("hibernateCursorJob")
//                .start(copyFileStep())
//                .build();
//    }
//
//    @Bean
//    public Step copyFileStep() {
//        return this.stepBuilderFactory.get("copyFileStep")
//                .<Customer, Customer>chunk(10)
//                .reader(customerItemReader(null, null))
//                .writer(itemWriter())
//                .build();
//    }
//
////    @Bean
////    @StepScope
////    public HibernateCursorItemReader<Customer> customerItemReader(
////            EntityManagerFactory entityManagerFactory,
////            @Value("#{jobParameters['city']}") String city
////    ) {
////
////        return new HibernateCursorItemReaderBuilder<Customer>()
////                .name("customerItemReader")
////                .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
////                .queryString("from Customer where city = :city")
////                .parameterValues(Collections.singletonMap("city", city))
////                .build();
////    }
//
//    @Bean
//    @StepScope
//    public HibernatePagingItemReader<Customer> customerItemReader(
//        EntityManagerFactory entityManagerFactory,
//            @Value("#{jobParameters['city']}") String city
//    ) {
//
//        return new HibernatePagingItemReaderBuilder<Customer>()
//                .name("customerItemReader")
//                .sessionFactory(entityManagerFactory.unwrap(SessionFactory.class))
//                .queryString("from Customer where city = :city")
//                .parameterValues(Collections.singletonMap("city", city))
//                .pageSize(10)
//                .build();
//    }
//
//    @Bean
//    public ItemWriter<Customer> itemWriter() {
//        return (items) -> items.forEach(System.out::println);
//    }

}

