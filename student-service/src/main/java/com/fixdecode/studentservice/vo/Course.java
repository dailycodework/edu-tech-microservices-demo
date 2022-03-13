package com.fixdecode.studentservice.vo;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String id;
    private String name;
    private Integer modules;
    private String duration;
    private String instructorId;
    private List<String> students;
}
