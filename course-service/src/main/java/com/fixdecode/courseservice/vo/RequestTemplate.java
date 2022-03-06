package com.fixdecode.courseservice.vo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
@AllArgsConstructor
public class RequestTemplate {
    private RestTemplate restTemplate;

   
    public Object[] getCourseStudents(Set<String> studentsIds) {
        return restTemplate.getForObject("http://localhost:8002/api/students/student/selected/"+studentsIds, Object[].class);
    }    
    
    public Instructor getCourseInstructor(String instructorId){
        return restTemplate.getForObject(""+ instructorId, Instructor.class);
    }
}
