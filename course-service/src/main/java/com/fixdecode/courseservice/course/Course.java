package com.fixdecode.courseservice.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private Integer modules;
    private String duration;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "course_students", joinColumns = @JoinColumn(name = "course_id"))
    private Set<String> students = new HashSet<>();
    private String instructorId;

    public void addStudentsToCourses(Set<String> newStudent){
        var currentStudents = this.getStudents();
        currentStudents.addAll(newStudent);
        this.setStudents(currentStudents);
    }
}
