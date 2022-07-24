package io.spring.batch.helloworldbatch.skipper;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ParseException;

import java.io.FileNotFoundException;

public class FileVerificationSkipper implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable throwable, int i) throws SkipLimitExceededException {
        if(throwable instanceof FileNotFoundException) {
            return false;
        } else if (throwable instanceof ParseException && i <= 10) {
            return true;
        } else {
            return false;
        }
    }
}
