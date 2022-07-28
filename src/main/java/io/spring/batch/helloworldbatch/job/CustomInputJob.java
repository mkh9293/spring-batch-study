package io.spring.batch.helloworldbatch.job;


//import io.spring.batch.helloworldbatch.domain.Customer;
//import io.spring.batch.helloworldbatch.listener.CustomerItemListener;
//import io.spring.batch.helloworldbatch.reader.CustomerItemReader;
//import io.spring.batch.helloworldbatch.service.CustomerService;
//import io.spring.batch.helloworldbatch.skipper.FileVerificationSkipper;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.adapter.ItemReaderAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

//@EnableBatchProcessing
//@SpringBootApplication
public class CustomInputJob {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public ItemWriter<Customer> itemWriter() {
//        return (items) -> items.forEach(System.out::println);
//    }
//
//    @Bean
//    public CustomerItemReader customerItemReader() {
//        CustomerItemReader customerItemReader = new CustomerItemReader();
//        customerItemReader.setName("customerItemReader");
//        return customerItemReader;
//    }
//
//    @Bean
//    public Step copyFileStep() {
//        return this.stepBuilderFactory.get("copyFileStep")
//                .<Customer, Customer>chunk(10)
//                .reader(customerItemReader())
//                .writer(itemWriter())
//                .faultTolerant()
////                .skip(Exception.class)
////                .noSkip(ParseException.class)
////                .skipLimit(10)
//                .skipPolicy(new FileVerificationSkipper())
//                .listener(new CustomerItemListener())
//                .build();
//    }
//
//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("job")
//                .start(copyFileStep())
//                .build();
//    }
}
