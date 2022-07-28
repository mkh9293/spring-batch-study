package io.spring.batch.helloworldbatch.listener;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;

//public class CustomerItemListener {
//
//    private static final Log logger = LogFactory.getLog(CustomerItemListener.class);
//
//    @OnReadError
//    public void onReadError(Exception e) {
//        if(e instanceof FlatFileParseException) {
//            FlatFileParseException ffpe = (FlatFileParseException) e;
//
//            StringBuilder builder = new StringBuilder();
//            builder.append("An error occured while processing the " + ffpe.getLineNumber() +
//                    " line of the file. Below was the faulty input \n");
//            builder.append(ffpe.getInput() + "\n");
//
//            logger.error(builder.toString(), ffpe);
//        }
//    }
//
//    // 읽을 아이템이 없어도 Complete 되지 않고 계속 실행되도록 한다.
//    @AfterStep
//    public ExitStatus afterStep(StepExecution execution) {
//        if(execution.getReadCount() > 0) {
//            return execution.getExitStatus();
//        } else {
//            return ExitStatus.FAILED;
//        }
//    }
//}
