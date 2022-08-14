package com.apress.batch.chapter10.classifier;

import com.apress.batch.chapter10.domain.CustomerAddressUpdate;
import com.apress.batch.chapter10.domain.CustomerContactUpdate;
import com.apress.batch.chapter10.domain.CustomerNameUpdate;
import com.apress.batch.chapter10.domain.CustomerUpdate;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.classify.Classifier;

public class CustomerUpdateClassifier implements Classifier<CustomerUpdate, ItemWriter<? super CustomerUpdate>> {

    private final JdbcBatchItemWriter<CustomerUpdate> recordType1ItemWriter;
    private final JdbcBatchItemWriter<CustomerUpdate> recordType2ItemWriter;
    private final JdbcBatchItemWriter<CustomerUpdate> recordType3ItemWriter;

    public CustomerUpdateClassifier(JdbcBatchItemWriter<CustomerUpdate> recordType1ItemWriter, JdbcBatchItemWriter<CustomerUpdate> recordType2ItemWriter, JdbcBatchItemWriter<CustomerUpdate> recordType3ItemWriter) {
        this.recordType1ItemWriter = recordType1ItemWriter;
        this.recordType2ItemWriter = recordType2ItemWriter;
        this.recordType3ItemWriter = recordType3ItemWriter;
    }

    @Override
    public ItemWriter<? super CustomerUpdate> classify(CustomerUpdate customerUpdate) {
        if(customerUpdate instanceof CustomerNameUpdate) {
            return recordType1ItemWriter;
        } else if (customerUpdate instanceof CustomerAddressUpdate) {
            return recordType2ItemWriter;
        } else if (customerUpdate instanceof CustomerContactUpdate) {
            return recordType3ItemWriter;
        } else {
            throw new IllegalArgumentException("Invalid type: " + customerUpdate.getClass().getCanonicalName());
        }
    }
}
