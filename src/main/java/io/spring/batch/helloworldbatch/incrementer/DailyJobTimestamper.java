package io.spring.batch.helloworldbatch.incrementer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.util.Date;

public class DailyJobTimestamper implements JobParametersIncrementer {

    // 동일한 파라미터로 잡을 실행시킬때 발생하는 이슈를 회파히기 위해 incrementer 를 추가한다.
    @Override
    public JobParameters getNext(JobParameters jobParameters) {
        return new JobParametersBuilder(jobParameters)
                .addDate("currentDate", new Date())
                .toJobParameters();
    }

}
