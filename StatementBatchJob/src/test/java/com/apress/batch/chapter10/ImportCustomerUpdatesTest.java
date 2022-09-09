package com.apress.batch.chapter10;

import com.apress.batch.chapter10.configuration.ImportJobConfiguration;
import com.apress.batch.chapter10.processor.AccountItemProcessor;
import com.apress.batch.chapter10.validator.CustomerItemValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@JdbcTest
@ContextConfiguration(classes = {
        ImportJobConfiguration.class,
        CustomerItemValidator.class,
        AccountItemProcessor.class,
        BatchAutoConfiguration.class
})
@SpringBatchTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ImportCustomerUpdatesTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private DataSource datasource;

    private JdbcOperations jdbcOperations;

    @BeforeEach
    public void setUp() {
        this.jdbcOperations = new JdbcTemplate(this.datasource);
    }
}
