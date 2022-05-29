package io.spring.batch.helloworldbatch.validator;

import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class CompositeParameterValidator {

    // 2개 이상의 validator 를 사용해야하는 경우 CompositeJobParametersValidator 를 사용한다.
    @Bean
    public CompositeJobParametersValidator compositeValidator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();

        DefaultJobParametersValidator defaultJobParametersValidator = new DefaultJobParametersValidator(
                new String[] {"fileName"},
                new String[] {"name", "currentDate"}
        );

        defaultJobParametersValidator.afterPropertiesSet();

        validator.setValidators(
                Arrays.asList(
                        new ParameterValidator(),
                        defaultJobParametersValidator
                )
        );

        return validator;
    }

}
