package io.spring.batch.helloworldbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

// job 시작 전, 후 에 실행할 작업들을 명시할 수 있다.
public class JobLoggerListener implements JobExecutionListener {

    private static String START_MESSAGE = "%s is beginning execution";
    private static String END_MESSAGE = "%s has completed with the status %s";

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(String.format(START_MESSAGE, jobExecution.getJobInstance().getJobName()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(
                String.format(END_MESSAGE, jobExecution.getJobInstance().getJobName(), jobExecution.getStatus())
        );
    }
}
