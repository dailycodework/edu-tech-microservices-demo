package com.fixdecode.studentservice.vo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
@AllArgsConstructor
public class RequestTemplate {
    private RestTemplate restTemplate;

    public Object[] getStudentCourses(Set<String> coursesIds) {
        return restTemplate.getForObject(""+coursesIds, Object[].class);
    }

}
