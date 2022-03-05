package com.fixdecode.studentservice.vo;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String id;
    private String name;
    private Integer modules;
    private String duration;
    private String instructorId;
}
