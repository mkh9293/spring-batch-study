package io.spring.batch.helloworldbatch.tokenizer;

import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FieldSetFactory;
import org.springframework.batch.item.file.transform.LineTokenizer;

import java.util.ArrayList;
import java.util.List;

//public class CustomerLineTokenizer implements LineTokenizer {

//    private String[] names = new String[] {
//            "firstName", "middleInitial", "lastName", "addressNumber", "city", "state","zipCode"
//    };
//
//    private String delimiter = ",";
//
//    private FieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();
//
//    @Override
//    public FieldSet tokenize(String s) {
//
//        String[] fields = s.split(delimiter);
//
//        List<String> parsedFields = new ArrayList<>();
//
//        for(int i=0; i<fields.length; i++) {
//            if(i==4) {
//                parsedFields.set(i-1, parsedFields.get(i-1) + " " + fields[i]);
//            } else {
//                parsedFields.add(fields[i]);
//            }
//        }
//
//        return fieldSetFactory.create(parsedFields.toArray(new String[0]), names);
//    }
//}
