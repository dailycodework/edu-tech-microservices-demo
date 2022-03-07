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

    @PostMapping(UrlMapping.REGISTER_STUDENTS_FOR_COURSES)
    public ResponseEntity<List<Student>> registerStudentsForCourses(
            @RequestParam("studentsIds") Set<String> studentsIds,
            @RequestParam("coursesIds") Set<String> coursesIds){
        return new ResponseEntity<>(studentService.registerStudents(studentsIds,coursesIds),HttpStatus.OK);
    }

    @GetMapping(UrlMapping.GET_STUDENT_BY_ID)
    public ResponseEntity<VOTemplate> getStudentWithCourses(@PathVariable("id") String studentId){
        return new ResponseEntity<>(studentService.getStudentWithCourses(studentId), HttpStatus.FOUND);
    }

    @GetMapping(UrlMapping.GET_SELECTED_STUDENT_BY_ID)
    public ResponseEntity<List<Student>> getSelectedStudents(@PathVariable("ids") Set<String> studentIds){
        List<Student> students = studentService.getSelectedStudents(studentIds);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PutMapping(UrlMapping.UPDATE_STUDENT)
    public ResponseEntity<Student> updateStudent(@RequestBody Student theStudent){
        return new ResponseEntity<>(studentService.updateStudent(theStudent), HttpStatus.OK);
   }

}
