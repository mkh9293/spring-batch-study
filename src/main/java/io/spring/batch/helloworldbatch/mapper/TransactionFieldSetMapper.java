package io.spring.batch.helloworldbatch.mapper;

import io.spring.batch.helloworldbatch.domain.Transaction;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class TransactionFieldSetMapper implements FieldSetMapper<Transaction> {

    @Override
    public Transaction mapFieldSet(FieldSet fieldSet) throws BindException {
        Transaction transaction = new Transaction();

        transaction.setAccountNumber(fieldSet.readString("accountNumber"));
        transaction.setAmount(fieldSet.readDouble("amount"));
        transaction.setTransactionDate(fieldSet.readDate("transactionDate", "yyyy-MM-dd HH:mm:ss"));

        return transaction;
    }
}