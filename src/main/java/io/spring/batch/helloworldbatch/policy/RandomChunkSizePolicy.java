package io.spring.batch.helloworldbatch.policy;

import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.Random;

public class RandomChunkSizePolicy implements CompletionPolicy {

    private int chunkSize;
    private int totalProcessed;
    private Random random = new Random();

    @Override
    public boolean isComplete(RepeatContext repeatContext, RepeatStatus repeatStatus) {
        if(RepeatStatus.FINISHED == repeatStatus) {
            return true;
        } else {
            return isComplete(repeatContext);
        }
    }

    @Override
    public boolean isComplete(RepeatContext repeatContext) {
        return this.totalProcessed >= chunkSize;
    }

    @Override
    public RepeatContext start(RepeatContext repeatContext) {
        this.chunkSize = random.nextInt(20);
        this.totalProcessed = 0;

        System.out.println("The chunk size has been set to " + this.chunkSize);

        return repeatContext;
    }

    @Override
    public void update(RepeatContext repeatContext) {
        this.totalProcessed++;
    }
}
