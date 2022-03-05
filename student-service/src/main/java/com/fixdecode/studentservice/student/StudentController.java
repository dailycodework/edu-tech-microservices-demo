package com.fixdecode.studentservice.student;


import com.fixdecode.studentservice.util.UrlMapping;
import com.fixdecode.studentservice.vo.VOTemplate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@AllArgsConstructor
@RequestMapping(UrlMapping.STUDENTS)
public class StudentController {
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudent(){
        List<Student> allStudent = studentService.getStudents();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student theStudent){
        return new ResponseEntity<>(studentService.addStudent(theStudent), HttpStatus.CREATED);

    }

    @GetMapping(UrlMapping.EMAIL)
    public ResponseEntity<Student> getStudent(@PathVariable("email") String email){
        return new ResponseEntity<>(studentService.getStudent(email), HttpStatus.OK);
    }

    @GetMapping(UrlMapping.STUDENT_EMAIL)
    public ResponseEntity<VOTemplate> getStudentWithCourses(@PathVariable("email") String email){
        return new ResponseEntity<>(studentService.getStudentWithCourses(email), HttpStatus.OK);
    }

    @GetMapping(UrlMapping.SELECTED_STUDENT_IDS)
    public ResponseEntity<List<Student>> getSelectedStudents(@PathVariable("ids") Set<String> studentIds){
        List<Student> students = studentService.getSelectedStudents(studentIds);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
