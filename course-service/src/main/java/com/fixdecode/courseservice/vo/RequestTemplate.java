package com.fixdecode.courseservice.vo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
@AllArgsConstructor
public class RequestTemplate {
    private RestTemplate restTemplate;

    //Make a rest call to get all students from the student-service
    public Student[] getSelectedStudents(Set<String> studentsIds) {
        ResponseEntity<Student[]> studentEntity = restTemplate.getForEntity("http://STUDENT-SERVICE/api/v1/students/student/selected/"+
                studentsIds, Student[].class);
        return studentEntity.getBody();
    }

    //Make a rest call to get the instructor from the instructor-service
    public Instructor getCourseInstructor(String instructorId){
        return restTemplate.getForObject(""+ instructorId, Instructor.class);
    }
}
