package io.spring.batch.helloworldbatch.job;

import io.spring.batch.helloworldbatch.domain.Customer;
import io.spring.batch.helloworldbatch.mapper.CustomerRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@EnableBatchProcessing
//@SpringBootApplication
public class JdbcPagingJob {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return this.jobBuilderFactory.get("job")
//                .start(step())
//                .build();
//    }
//
//    @Bean
//    public Step step() {
//        return this.stepBuilderFactory.get("step")
//                .<Customer, Customer>chunk(10)
//                .reader(customerItemReader(null, null, null))
//                .writer(itemWriter())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcPagingItemReader<Customer> customerItemReader(
//            DataSource dataSource,
//            PagingQueryProvider pagingQueryProvider,
//            @Value("#{jobParameters['city']}") String city
//    ) {
//        Map<String, Object> parameterValues = new HashMap<>(1);
//        parameterValues.put("city", city);
//
//        return new JdbcPagingItemReaderBuilder<Customer>()
//                .name("customerItemReader")
//                .dataSource(dataSource)
//                .queryProvider(pagingQueryProvider)
//                .parameterValues(parameterValues)
//                .pageSize(10)
//                .rowMapper(new CustomerRowMapper())
//                .build();
//    }
//
//    @Bean
//    public SqlPagingQueryProviderFactoryBean pagingQueryProviderFactoryBean(DataSource dataSource) {
//        SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();
//
//        factoryBean.setSelectClause("select *");
//        factoryBean.setFromClause("from customer");
//        factoryBean.setWhereClause("where city = :city");
//        factoryBean.setSortKey("lastName");
//        factoryBean.setDataSource(dataSource);
//
//        return factoryBean;
//    }
//
//    @Bean
//    public ItemWriter itemWriter() {
//        return (items) -> items.forEach(System.out::println);
//    }
}
