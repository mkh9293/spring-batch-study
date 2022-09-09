package com.apress.batch.chapter10.validator;

import com.apress.batch.chapter10.domain.CustomerUpdate;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

@Component
public class CustomerItemValidator implements Validator<CustomerUpdate> {

    protected final NamedParameterJdbcTemplate jdbcTemplate;

    protected static final String FIND_CUSTOMER = "SELECT COUNT(*) FROM CUSTOMER WHERE customer_id = :id";

    public CustomerItemValidator(NamedParameterJdbcTemplate template) {
        this.jdbcTemplate = template;
    }

    @Override
    public void validate(CustomerUpdate customerUpdate) throws ValidationException {
        Map<String, Long> parameterMap = Collections.singletonMap("id", customerUpdate.getCustomerId());

        Long count = jdbcTemplate.queryForObject(FIND_CUSTOMER, parameterMap, Long.class);

        if(count == 0) {
            throw new ValidationException(
                    String.format("Customer id %s was not able to found", customerUpdate.getCustomerId())
            );
        }
    }
}
