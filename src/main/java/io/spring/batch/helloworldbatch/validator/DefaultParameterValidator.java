package io.spring.batch.helloworldbatch.validator;

import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.context.annotation.Bean;

//public class DefaultParameterValidator {
//
//    // JobParametersValidator 인터페이스를 구현하지 않고 DefaultJobParametersValidator 클래스를 직접 이용하여 validator 를 구현한다.
//    // fileName 은 필수로 입력되어야 하고, name 은 상관없음.
//    // 이외 나머지 파라미터가 들어오면 에러 출력.
//    @Bean
//    public JobParametersValidator validator() {
//        DefaultJobParametersValidator validator = new DefaultJobParametersValidator();
//
//        validator.setRequiredKeys(new String[] {"fileName"});
//        validator.setOptionalKeys(new String[] {"name"});
//
//        return validator;
//    }
//}
