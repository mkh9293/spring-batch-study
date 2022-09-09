package com.apress.batch.chapter10.processor;

import com.apress.batch.chapter10.domain.Statement;
import com.apress.batch.chapter10.extractor.AccountResultSetExtractor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;

@Component
public class AccountItemProcessor implements ItemProcessor<Statement, Statement> {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AccountItemProcessor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Statement process(Statement item) throws Exception {

//        int threadCount = 10;
//        CountDownLatch doneSignal = new CountDownLatch(threadCount);
//
//        for(int i=0; i<threadCount; i++) {
//            Thread thread = new Thread(() -> {
//                for(int j=0; j<1000000; j++) {
//                    new BigInteger(String.valueOf(j)).isProbablePrime(0);
//                }
//                doneSignal.countDown();
//            });
//
//            thread.start();
//        }
//        doneSignal.await();


        item.setAccounts(this.jdbcTemplate.query("select a.account_id," +
                        "       a.balance," +
                        "       a.last_statement_date," +
                        "       t.transaction_id," +
                        "       t.description," +
                        "       t.credit," +
                        "       t.debit," +
                        "       t.timestamp " +
				"from account a left join " +  //MYSQL
				"    transaction t on a.account_id = t.account_account_id " +
                        "where a.account_id in " +
                        "	(select account_account_id " +
                        "	from customer_account " +
                        "	where customer_customer_id = ?) " +
                        "order by t.timestamp",
                new Object[] {item.getCustomer().getId()},
                new AccountResultSetExtractor()));

        return item;
    }
}
