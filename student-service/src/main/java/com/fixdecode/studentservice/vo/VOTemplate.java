package com.fixdecode.studentservice.vo;

import com.fixdecode.studentservice.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOTemplate {
    private List<Course> courses;
    private List<Student> students;
}
