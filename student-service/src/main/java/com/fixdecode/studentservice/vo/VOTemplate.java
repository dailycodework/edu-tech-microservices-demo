package com.fixdecode.studentservice.vo;

import com.fixdecode.studentservice.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOTemplate {
    private Student student;
    private Object[] courses;
}
