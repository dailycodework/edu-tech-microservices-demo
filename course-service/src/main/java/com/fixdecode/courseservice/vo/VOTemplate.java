package com.fixdecode.courseservice.vo;

import com.fixdecode.courseservice.course.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOTemplate {
    private Instructor instructor;
    private List<Student> students;
    private List<Course> courses;
}
