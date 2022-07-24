package io.spring.batch.helloworldbatch.job;

import io.spring.batch.helloworldbatch.domain.Customer;
import io.spring.batch.helloworldbatch.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

//@EnableBatchProcessing
//@SpringBootApplication
//@EnableJpaRepositories({"io.spring.batch.helloworldbatch.repository"})
//@EntityScan(basePackages="io.spring.batch.helloworldbatch")
public class SpringDataRepositoryJob {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//	@StepScope
//	public RepositoryItemReader<Customer> customerItemReader(
//			CustomerRepository customerRepository,
//            @Value("#{jobParameters['city']}") String city
//	) {
//    	return new RepositoryItemReaderBuilder<Customer>()
//				.name("customerItemReader")
//				.arguments(Collections.singletonList(city))
//				.methodName("findByCity")
//				.repository(customerRepository)
//				.sorts(Collections.singletonMap("lastName", Sort.Direction.ASC))
//				.build();
//	}
//
//    @Bean
//	public ItemWriter<Customer> itemWriter() {
//		return (items) -> items.forEach(System.out::println);
//	}
//
//	@Bean
//	public Step copyFileStep() {
//		return this.stepBuilderFactory.get("copyFileStep")
//				.<Customer, Customer>chunk(10)
//				.reader(customerItemReader(null, null))
//				.writer(itemWriter())
//				.build();
//	}
//
//	@Bean
//	public Job job() {
//		return this.jobBuilderFactory.get("job")
//				.start(copyFileStep())
//				.build();
//	}

}
