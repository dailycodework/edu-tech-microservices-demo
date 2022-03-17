package com.fixdecode.studentservice.vo;

import com.fixdecode.studentservice.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Component
@AllArgsConstructor
public class RequestTemplate {
    private RestTemplate restTemplate;
    private final String COURSE_URL="http://COURSE-SERVICE/api/v1/courses/";

    public Course[] getSelectedCourses(Set<String> coursesIds) {
        String STUDENT_COURSES = COURSE_URL+"/get-selected-courses/";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(STUDENT_COURSES)
                .queryParam("coursesIds",coursesIds);
        return restTemplate.getForObject(builder.buildAndExpand().toUri(),Course[].class);
    }

    public Student[] registerStudentForCourses(Set<String> coursesIds, Set<String> studentsIds) {
        String COURSE_REGISTRATION =COURSE_URL+"/register-students/";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(COURSE_REGISTRATION)
                .queryParam("coursesIds",coursesIds)
                .queryParam("studentsIds", studentsIds);
        return restTemplate
                .postForObject(builder
                .buildAndExpand()
                .toUri(),HttpMethod.POST,Student[].class);
    }
}
