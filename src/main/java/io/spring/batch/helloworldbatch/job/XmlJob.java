package io.spring.batch.helloworldbatch.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.batch.helloworldbatch.domain.Customer;
import io.spring.batch.helloworldbatch.domain.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.text.SimpleDateFormat;

//@EnableBatchProcessing
//@SpringBootApplication
public class XmlJob {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("multiLineJob")
//                .start(copyFileStep())
//                .build();
//    }
//
//    @Bean
//    public Step copyFileStep() {
//        return this.stepBuilderFactory.get("copyFileStep")
//                .<Customer, Customer>chunk(10)
//                .reader(customerFileReader(null))
//                .writer(itemWriter())
//                .build();
//    }
//
//    @Bean
//    public ItemWriter itemWriter() {
//        return (items) -> items.forEach(System.out::println);
//    }
//
//    @Bean
//    @StepScope
//    public JsonItemReader<Customer> customerFileReader(
//            @Value("#{jobParameters['customerFile']}") Resource resource
//    ) {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
//
//        JacksonJsonObjectReader<Customer> jsonObjectReader = new JacksonJsonObjectReader<>(Customer.class);
//        jsonObjectReader.setMapper(objectMapper);
//
//        return new JsonItemReaderBuilder<Customer>()
//                .name("customerFileReader")
//                .jsonObjectReader(jsonObjectReader)
//                .resource(resource)
//                .build();
//    }
//
//    // xml reader
////    @Bean
////    @StepScope
////    public StaxEventItemReader<Customer> customerFileReader(
////            @Value("#{jobParameters['customerFile']}") Resource resource
////    ) {
////        return new StaxEventItemReaderBuilder<Customer>()
////                .name("customerFileReader")
////                .resource(resource)
////                .addFragmentRootElements("customer")
////                .unmarshaller(customerMarshaller())
////                .build();
////    }
//
//    @Bean
//    public Jaxb2Marshaller customerMarshaller() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//
//        jaxb2Marshaller.setClassesToBeBound(Customer.class, Transaction.class);
//
//        return jaxb2Marshaller;
//    }
}
