package com.webflux.WebfluxDemo.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webflux.WebfluxDemo.entity.EmployeeEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import org.json.JSONObject;

@Component
public class EmployeeValidator {
    @Autowired
    private ObjectMapper objectMapper;


    public Mono<EmployeeEntity> validateInputAgainstSchema(EmployeeEntity employeeEntity) {

        try {
            JSONObject input = convertRuleAsJSONObject(employeeEntity);
            System.out.println(input);
        } catch ( JsonProcessingException e) {
            return Mono.error(new Exception());
        }
        return Mono.just(employeeEntity);

    }
    private JSONObject convertRuleAsJSONObject(EmployeeEntity employeeEntity) throws JsonProcessingException {
        String ruleObjectAsString = objectMapper.writeValueAsString(employeeEntity);
        return objectMapper.convertValue(ruleObjectAsString, JSONObject.class);
    }
}
