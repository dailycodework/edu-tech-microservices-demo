package com.fixdecode.studentservice.student;


import com.fixdecode.studentservice.util.UrlMapping;
import com.fixdecode.studentservice.vo.VOTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
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
            @RequestParam("coursesIds") Set<String> coursesIds,
            @RequestParam("studentsIds") Set<String> studentsIds){
        return new ResponseEntity<>(studentService.registerStudentsForCourses(coursesIds, studentsIds),HttpStatus.OK);
    }

   @GetMapping(UrlMapping.GET_STUDENT_WITH_COURSES)
    public ResponseEntity<VOTemplate> getStudentWithCourses(@PathVariable("id") String studentId){
        return new ResponseEntity<>(studentService.getStudentWithCourses(studentId), HttpStatus.FOUND);
    }

    @GetMapping(UrlMapping.GET_SELECTED_STUDENTS)
    public ResponseEntity<List<Student>> getSelectedStudentsById(@RequestParam("ids") Set<String> ids){
        List<Student> students = studentService.getSelectedStudentsById(ids);
        return new ResponseEntity<>(students, HttpStatus.FOUND);
    }

    @PutMapping(UrlMapping.UPDATE_STUDENT)
    public ResponseEntity<Student> updateStudent(@RequestBody Student theStudent){
        return new ResponseEntity<>(studentService.updateStudent(theStudent), HttpStatus.OK);
   }
}
