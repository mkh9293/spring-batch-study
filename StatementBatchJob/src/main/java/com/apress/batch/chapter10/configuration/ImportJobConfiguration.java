package com.apress.batch.chapter10.configuration;

import com.apress.batch.chapter10.aggregator.StatementLineAggregator;
import com.apress.batch.chapter10.callback.StatementHeaderCallback;
import com.apress.batch.chapter10.classifier.CustomerUpdateClassifier;
import com.apress.batch.chapter10.domain.*;
import com.apress.batch.chapter10.processor.AccountItemProcessor;
import com.apress.batch.chapter10.validator.CustomerItemValidator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class ImportJobConfiguration {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() throws Exception {
//        return this.jobBuilderFactory.get("importJob")
////                .start(importCustomerUpdates())
////                .next(importTransactions())
////                .next(applyTransactions())
////                .next(generateStatements(null))
//
////                .start(applyTransactions())
//
//                .start(generateStatements(null))
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }
//
//    @Bean
//    public Step generateStatements(AccountItemProcessor itemProcessor) {
//        return this.stepBuilderFactory.get("generateStatements")
//                .<Statement, Statement>chunk(1)
//                .reader(statementItemReader(null))
//                .processor(itemProcessor)
//                .writer(statementItemWriter(null))
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public MultiResourceItemWriter<Statement> statementItemWriter(
//            @Value("#{jobParameters['outputDirectory']}") Resource resource
//    ) {
//        return new MultiResourceItemWriterBuilder<Statement>()
//                .name("statementItemWriter")
//                .resource(resource)
//                .itemCountLimitPerResource(1)
//                .delegate(individualStatementItemWriter())
//                .build();
//    }
//
//    @Bean
//    public FlatFileItemWriter<Statement> individualStatementItemWriter() {
//        return new FlatFileItemWriterBuilder<Statement>()
//                .name("individualStatementItemWriter")
//                .headerCallback(new StatementHeaderCallback())
//                .lineAggregator(new StatementLineAggregator())
//                .build();
//    }
//
//    @Bean
//    public JdbcCursorItemReader<Statement> statementItemReader(DataSource dataSource) {
//        return new JdbcCursorItemReaderBuilder<Statement>()
//                .name("statementItemReader")
//                .dataSource(dataSource)
//                .sql("SELECT * FROM CUSTOMER")
//                .rowMapper((resultSet, i) -> {
//                    Customer customer = new Customer(resultSet.getLong("customer_id"),
//                            resultSet.getString("first_name"),
//                            resultSet.getString("middle_name"),
//                            resultSet.getString("last_name"),
//                            resultSet.getString("address1"),
//                            resultSet.getString("address2"),
//                            resultSet.getString("city"),
//                            resultSet.getString("state"),
//                            resultSet.getString("postal_code"),
//                            resultSet.getString("ssn"),
//                            resultSet.getString("email_address"),
//                            resultSet.getString("home_phone"),
//                            resultSet.getString("cell_phone"),
//                            resultSet.getString("work_phone"),
//                            resultSet.getInt("notification_pref"));
//
//                    return new Statement(customer);
//                }).build();
//    }
//
//
//
//    /**
//     * -- apply transaction
//     */
//
//    @Bean
//    public Step applyTransactions() {
//        return this.stepBuilderFactory.get("applyTransactions")
//                .<Transaction, Transaction>chunk(100)
//                .reader(applyTransactionReader(null))
//                .writer(applyTransactionWriter(null))
//                .build();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Transaction> applyTransactionWriter(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Transaction>()
//                .dataSource(dataSource)
//                .sql("update account set balance = balance + :transactionAmount " +
//                        "where account_id = :accountId")
//                .beanMapped()
//                .assertUpdates(false)
//                .build();
//    }
//
//    @Bean
//    public JdbcCursorItemReader<Transaction> applyTransactionReader(DataSource dataSource) {
//        return new JdbcCursorItemReaderBuilder<Transaction>()
//                .name("applyTransactionReader")
//                .dataSource(dataSource)
//                .sql("select transaction_id, account_account_id, description, credit, debit, timestamp " +
//                        "from transaction order by timestamp")
//                .rowMapper((resultSet, i) ->
//                        new Transaction(
//                                resultSet.getLong("transaction_id"),
//                                resultSet.getLong("account_account_id"),
//                                resultSet.getString("description"),
//                                resultSet.getBigDecimal("credit"),
//                                resultSet.getBigDecimal("debit"),
//                                resultSet.getTimestamp("timestamp")
//                        )
//                )
//                .build();
//    }
//
//
//    /**
//     * --- transaction update
//     */
//
//    @Bean
//    public Step importTransactions() {
//        return this.stepBuilderFactory.get("importTransactions")
//                .<Transaction, Transaction>chunk(100)
//                .reader(transactionItemReader(null))
//                .writer(transactionItemWriter(null))
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public StaxEventItemReader<Transaction> transactionItemReader(
//            @Value("#{jobParameters['transactionFile']}") Resource transactionFile
//    ) {
//
//        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
//        unmarshaller.setClassesToBeBound(Transaction.class);
//
//        return new StaxEventItemReaderBuilder<Transaction>()
//                .name("fooReader")
//                .resource(transactionFile)
//                .addFragmentRootElements("transaction")
//                .unmarshaller(unmarshaller)
//                .build();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Transaction> transactionItemWriter(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Transaction>()
//                .dataSource(dataSource)
//                .sql("INSERT INTO TRANSACTION (TRANSACTION_ID, " +
//                        "ACCOUNT_ACCOUNT_ID, " +
//                        "DESCRIPTION, " +
//                        "CREDIT, " +
//                        "DEBIT, " +
//                        "TIMESTAMP) VALUES (:transactionId, " +
//                        ":accountId, " +
//                        ":description, " +
//                        ":credit, " +
//                        ":debit, " +
//                        ":timestamp)")
//                .beanMapped()
//                .build();
//    }
//
//
//    /**
//     * --- customer update
//     */
//
//    @Bean
//    public Step importCustomerUpdates() throws Exception {
//        return this.stepBuilderFactory.get("importCustomerUpdates")
//                .<CustomerUpdate, CustomerUpdate>chunk(100)
//                .reader(customerUpdateItemReader(null))
//                .processor(customerUpdateValidatingItemProcessor(null))
//                .writer(customerUpdateItemWriter())
//                .build();
//    }
//
//    @Bean
//    public ClassifierCompositeItemWriter<CustomerUpdate> customerUpdateItemWriter() {
//        CustomerUpdateClassifier classifier = new CustomerUpdateClassifier(
//                customerNameUpdateItemWriter(null),
//                customerAddressUpdateItemWriter(null),
//                customerContactUpdateItemWriter(null)
//        );
//
//        ClassifierCompositeItemWriter<CustomerUpdate> compositeItemWriter = new ClassifierCompositeItemWriter<>();
//        compositeItemWriter.setClassifier(classifier);
//
//        return compositeItemWriter;
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<CustomerUpdate> customerContactUpdateItemWriter(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<CustomerUpdate>()
//                .beanMapped()
//                .sql("UPDATE CUSTOMER " +
//                        "SET EMAIL_ADDRESS = COALESCE(:emailAddress, EMAIL_ADDRESS), " +
//                        "HOME_PHONE = COALESCE(:homePhone, HOME_PHONE), " +
//                        "CELL_PHONE = COALESCE(:cellPhone, CELL_PHONE), " +
//                        "WORK_PHONE = COALESCE(:workPhone, WORK_PHONE), " +
//                        "NOTIFICATION_PREF = COALESCE(:notificationPreference, NOTIFICATION_PREF)" +
//                        "WHERE CUSTOMER_ID = :customerId")
//                .dataSource(dataSource)
//                .build();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<CustomerUpdate> customerAddressUpdateItemWriter(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<CustomerUpdate>()
//                .beanMapped()
//                .sql("UPDATE CUSTOMER " +
//                        "SET ADDRESS1 = COALESCE(:address1, ADDRESS1), " +
//                        "ADDRESS2 = COALESCE(:address2, ADDRESS2), " +
//                        "CITY = COALESCE(:city, CITY), " +
//                        "STATE = COALESCE(:state, STATE), " +
//                        "POSTAL_CODE = COALESCE(:postalCode, POSTAL_CODE)" +
//                        "WHERE CUSTOMER_ID = :customerId")
//                .dataSource(dataSource)
//                .build();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<CustomerUpdate> customerNameUpdateItemWriter(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<CustomerUpdate>()
//                .beanMapped()
//                .sql("UPDATE CUSTOMER " +
//                        "SET FIRST_NAME = COALESCE(:firstName, FIRST_NAME), " +
//                        "MIDDLE_NAME = COALESCE(:middleName, MIDDLE_NAME), " +
//                        "LAST_NAME = COALESCE(:lastName, LAST_NAME)" +
//                        "WHERE CUSTOMER_ID = :customerId")
//                .dataSource(dataSource)
//                .build();
//    }
//
//    @Bean
//    public ValidatingItemProcessor<CustomerUpdate> customerUpdateValidatingItemProcessor(
//            CustomerItemValidator validator
//    ) {
//        ValidatingItemProcessor<CustomerUpdate> customerUpdateValidatingItemProcessor =
//                new ValidatingItemProcessor<>(validator);
//
//        customerUpdateValidatingItemProcessor.setFilter(true);
//
//        return customerUpdateValidatingItemProcessor;
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<CustomerUpdate> customerUpdateItemReader(
//            @Value("#{jobParameters['customerUpdateFile']}") Resource resource
//    ) throws Exception {
//        return new FlatFileItemReaderBuilder<CustomerUpdate>()
//                .name("customerUpdateItemReader")
//                .resource(resource)
//                .lineTokenizer(customerUpdateLineTokenizer())
//                .fieldSetMapper(customerUpdateFieldSetMapper())
//                .build();
//    }
//
//    @Bean
//    public LineTokenizer customerUpdateLineTokenizer() throws Exception {
//        DelimitedLineTokenizer recordType1 = new DelimitedLineTokenizer();
//        recordType1.setNames("recordId", "customerId", "firstName", "middleName", "lastName");
//        recordType1.afterPropertiesSet();
//
//        DelimitedLineTokenizer recordType2 = new DelimitedLineTokenizer();
//        recordType2.setNames("recordId", "customerId", "address1", "address2", "city", "state", "postalCode");
//        recordType2.afterPropertiesSet();
//
//        DelimitedLineTokenizer recordType3 = new DelimitedLineTokenizer();
//        recordType3.setNames(
//                "recordId", "customerId", "emailAddress", "homePhone",
//                "cellPhone", "workPhone", "notificationPreference"
//        );
//        recordType3.afterPropertiesSet();
//
//        Map<String, LineTokenizer> tokenizer = new HashMap<>(3);
//        tokenizer.put("1*", recordType1);
//        tokenizer.put("2*", recordType2);
//        tokenizer.put("3*", recordType3);
//
//        PatternMatchingCompositeLineTokenizer lineTokenizer = new PatternMatchingCompositeLineTokenizer();
//        lineTokenizer.setTokenizers(tokenizer);
//
//        return lineTokenizer;
//    }
//
//    @Bean
//    public FieldSetMapper<CustomerUpdate> customerUpdateFieldSetMapper() {
//        return fieldSet -> {
//            switch (fieldSet.readInt("recordId")) {
//                case 1: return new CustomerNameUpdate(
//                        fieldSet.readLong("customerId"),
//                        fieldSet.readString("firstName"),
//                        fieldSet.readString("middleName"),
//                        fieldSet.readString("lastName")
//                );
//
//                case 2: return new CustomerAddressUpdate(
//                        fieldSet.readLong("customerId"),
//                        fieldSet.readString("address1"),
//                        fieldSet.readString("address2"),
//                        fieldSet.readString("city"),
//                        fieldSet.readString("state"),
//                        fieldSet.readString("postalCode")
//                );
//
//                case 3:
//                    String rawPreference = fieldSet.readString("notificationPreference");
//                    Integer notificationPreference = null;
//
//                    if(StringUtils.hasText(rawPreference)) {
//                        notificationPreference = Integer.parseInt(rawPreference);
//                    }
//
//                    return new CustomerContactUpdate(
//                            fieldSet.readLong("customerId"),
//                            fieldSet.readString("emailAddress"),
//                            fieldSet.readString("homePhone"),
//                            fieldSet.readString("cellPhone"),
//                            fieldSet.readString("workPhone"),
//                            notificationPreference
//                    );
//
//                default: throw new IllegalArgumentException(
//                        "Invalid record type was found: " + fieldSet.readInt("recordId")
//                );
//            }
//        };
//    }

}
