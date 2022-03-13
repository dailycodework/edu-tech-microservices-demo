package com.fixdecode.studentservice.vo;

import com.fixdecode.studentservice.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class RequestTemplate {
    private RestTemplate restTemplate;
    private final String COURSE_SERVICE ="http://COURSE-SERVICE/api/v1/courses/register-students/";


    public List<Course> getStudentCourses(Set<String> coursesIds) {
        VOTemplate response = restTemplate.getForObject(COURSE_SERVICE+"selected-courses"+coursesIds, VOTemplate.class);
        assert response != null;
        return response.getCourses();
    }

    public ResponseEntity<Student[]> registerStudentForCourses(Set<String> coursesIds, Set<String> studentsIds) {
      //  String path = "register-students-courses";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(COURSE_SERVICE)
                .queryParam("coursesIds",coursesIds)
                .queryParam("studentsIds", studentsIds);
        ResponseEntity<Student[]> response= restTemplate.postForEntity(builder.buildAndExpand().toUri() , HttpMethod.POST, Student[].class);
       Student[] feedBack = response.getBody();
       return new ResponseEntity<>(feedBack, HttpStatus.OK);
    }
}
