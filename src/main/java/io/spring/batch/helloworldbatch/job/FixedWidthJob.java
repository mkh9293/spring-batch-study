package io.spring.batch.helloworldbatch.job;

//import io.spring.batch.helloworldbatch.domain.Customer;
//import io.spring.batch.helloworldbatch.mapper.CustomerFieldSetMapper;
//import io.spring.batch.helloworldbatch.tokenizer.CustomerLineTokenizer;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.transform.Range;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.Resource;

//@EnableBatchProcessing
//@SpringBootApplication
public class FixedWidthJob {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("fixedWidthJob")
//                .start(copyFileStep())
//                .build();
//    }
//
//    @Bean
//    public Step copyFileStep() {
//        return this.stepBuilderFactory.get("copyFileStep")
//                .<Customer, Customer>chunk(10)
//                .reader(customerItemReader(null))
//                .writer(itemWriter())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Customer> customerItemReader(
//            @Value("#{jobParameters['customerFile']}") Resource inputFile
//    ) {
//
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("customerItemReader123")
//                .lineTokenizer(new CustomerLineTokenizer())
//                .targetType(Customer.class)
//                .resource(inputFile)
//                .build();
//    }
//
////    @Bean
////    @StepScope
////    public FlatFileItemReader<Customer> customerItemReader(
////            @Value("#{jobParameters['customerFile']}") Resource inputFile
////    ) {
////
////        return new FlatFileItemReaderBuilder<Customer>()
////                .name("customerItemReader123")
////                .delimited()
////                .names("firstName", "middleInitial", "lastName", "addressNumber", "street", "city", "state","zipCode")
////                .fieldSetMapper(new CustomerFieldSetMapper())
////                .resource(inputFile)
////                .build();
////    }
//
////    @Bean
////    @StepScope
////    public FlatFileItemReader<Customer> customerItemReader(
////            @Value("#{jobParameters['customerFile']}") Resource inputFile
////    ) {
////
////        return new FlatFileItemReaderBuilder<Customer>()
////                .name("customerItemReader123")
////                .resource(inputFile)
////                .saveState(false)
////                .fixedLength()
////                .columns(new Range[]{
////                        new Range(1,11), new Range(12, 12), new Range(13, 22),
////						new Range(23, 26), new Range(27,46), new Range(47,62), new Range(63,64),
////						new Range(65,69)
////                })
////                .names("firstName", "middleInitial", "lastName", "addressNumber", "street", "city", "state","zipCode")
////                .targetType(Customer.class)
////                .build();
////    }
//
//    @Bean
//    public ItemWriter<Customer> itemWriter() {
//        return (items) -> items.forEach(System.out::println);
//    }

}
