package com.fixdecode.courseservice.vo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
@AllArgsConstructor
public class RequestTemplate {
    private RestTemplate restTemplate;

    //Make a rest call to get all students from the student-service
    public Student[] getSelectedStudents(Set<String> studentsIds) {
        return restTemplate.getForObject("http://STUDENT-SERVICE/api/v1/students/selected-students/"+
                studentsIds, Student[].class);
    }

    //Make a rest call to get the instructor from the instructor-service
    public Instructor getCourseInstructor(String instructorId){
        return restTemplate.getForObject(""+ instructorId, Instructor.class);
    }
}
