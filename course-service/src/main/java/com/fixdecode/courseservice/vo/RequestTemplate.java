package com.fixdecode.courseservice.vo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Component
@AllArgsConstructor
public class RequestTemplate {
    private RestTemplate restTemplate;

    //Make a rest call to get all students from the student-service
    public List<Student> getSelectedStudents(Set<String> studentsIds) {
        ResponseEntity<Student[]> response =
        restTemplate.getForEntity("http://STUDENT-SERVICE/api/v1/students/selected-students/"+
                studentsIds, Student[].class);
        Student[] students = response.getBody();
        assert students != null;
        return Arrays.stream(students).collect(toList());
    }

    //Make a rest call to get the instructor from the instructor-service
    public Instructor getCourseInstructor(String instructorId){
        return restTemplate.getForObject(""+ instructorId, Instructor.class);
    }
}
