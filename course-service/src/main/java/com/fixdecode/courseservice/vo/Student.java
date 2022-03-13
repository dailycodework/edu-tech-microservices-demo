package com.fixdecode.courseservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> courses;

}
