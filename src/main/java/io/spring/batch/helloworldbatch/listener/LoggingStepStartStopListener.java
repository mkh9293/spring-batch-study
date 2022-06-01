package io.spring.batch.helloworldbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

// step 의 시작, 끝지점에서 실행할 작업을 명시한다.
// job 내에 여러개의 스텝이 있는 경우 각 스텝의 시작과 끝 지점에서 성공여부 및 무결성 등 여러가지 작업을 체크할 수 있다.
public class LoggingStepStartStopListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println(stepExecution.getStepName() + " has begun!");
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println(stepExecution.getStepName() + " has ended!");

        return stepExecution.getExitStatus();
    }
}
