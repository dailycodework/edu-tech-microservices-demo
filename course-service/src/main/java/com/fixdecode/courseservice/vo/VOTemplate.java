package com.fixdecode.courseservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VOTemplate {
    private Student student;
    private Instructor instructor;
    private Object[] students;
}
