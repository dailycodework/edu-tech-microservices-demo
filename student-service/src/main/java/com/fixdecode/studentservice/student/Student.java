package com.fixdecode.studentservice.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "students_courses", joinColumns = @JoinColumn(name = "students_id"))
    private Set<String> courses = new HashSet<>();


    public void registerStudentForCourses(Set<String> newCourses){
        var currentCourses = this.getCourses();
        currentCourses.addAll(newCourses);
        this.setCourses(currentCourses);
    }
}
