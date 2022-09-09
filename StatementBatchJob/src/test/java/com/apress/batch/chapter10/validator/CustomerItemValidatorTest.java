package com.apress.batch.chapter10.validator;

import com.apress.batch.chapter10.domain.CustomerUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class CustomerItemValidatorTest {

    @Mock
    private NamedParameterJdbcTemplate template;

    private CustomerItemValidator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.validator = new CustomerItemValidator(this.template);
    }

    @Test
    public void testValidCustomer() {
        // given
        CustomerUpdate customerUpdate = new CustomerUpdate(5L);

        // when
        ArgumentCaptor<Map<String, Long>> parameterMap = ArgumentCaptor.forClass(Map.class);
        when(this.template.queryForObject(eq(CustomerItemValidator.FIND_CUSTOMER), parameterMap.capture(), eq(Long.class)))
                .thenReturn(2L);

        this.validator.validate(customerUpdate);

        assertEquals(5L, (long) parameterMap.getValue().get("id"));
    }

    @Test
    public void testInvalidCustomer() {
        // given
        CustomerUpdate customerUpdate = new CustomerUpdate(5L);

        // when
        ArgumentCaptor<Map<String, Long>> parameterMap = ArgumentCaptor.forClass(Map.class);
        when(this.template.queryForObject(eq(CustomerItemValidator.FIND_CUSTOMER), parameterMap.capture(), eq(Long.class)))
                .thenReturn(0L);

        Throwable e = assertThrows(ValidationException.class, () -> this.validator.validate(customerUpdate));

        // then
        assertEquals("Customer id 5 was not able to found", e.getMessage());

    }

}
