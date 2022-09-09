package com.apress.batch.chapter10.validator;

import com.apress.batch.chapter10.domain.CustomerUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@JdbcTest
public class CustomerItemValidatorIntegrationTest {

    @Autowired
    private DataSource dataSource;

    private CustomerItemValidator validator;

    @BeforeEach
    public void setUp() {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(this.dataSource);
        this.validator = new CustomerItemValidator(template);
    }

    @Test
    public void testCustomers() {
        CustomerUpdate customerUpdate = new CustomerUpdate(5L);
        this.validator.validate(customerUpdate);
    }

    @Test
    public void testValidCustomer() {
        // given
        CustomerUpdate customerUpdate = new CustomerUpdate(-5L);

        // when
        ValidationException exception = assertThrows(ValidationException.class, () -> this.validator.validate(customerUpdate));

        // then
        assertEquals("Customer id -5 was not able to found", exception.getMessage());
    }

}
