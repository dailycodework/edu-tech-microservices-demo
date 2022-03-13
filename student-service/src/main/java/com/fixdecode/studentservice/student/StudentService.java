package com.fixdecode.studentservice.student;

import com.fixdecode.studentservice.exceptions.StudentNotFoundException;
import com.fixdecode.studentservice.util.FeedBackMessage;
import com.fixdecode.studentservice.vo.Course;
import com.fixdecode.studentservice.vo.RequestTemplate;
import com.fixdecode.studentservice.vo.VOTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private RequestTemplate requestTemplate;

    public List<Student> getStudents(){
        return studentRepository.findAll().stream().toList();
    }

    public Student addStudent(Student theStudent){
        return studentRepository.save(theStudent);
    }


    public List<Student> getSelectedStudentsById(Iterable<String> studentIds) {
        return studentRepository.findAllById(studentIds).stream().toList();
    }

    public Student updateStudent(Student theStudent) {
        var student = this.getStudentById(theStudent.getId());
        theStudent.setCourses(student.getCourses());
        return studentRepository.save(theStudent);
    }

    @Transactional
    public List<Student> registerStudentsForCourses(Set<String> coursesIds, Set<String> studentsIds) {
        var students = getSelectedStudentsById(studentsIds);
        var theStudents = students.stream().map(Student::getId).collect(toSet());
        //Making rest call to course-service to save students to selected courses.
      var response = requestTemplate.registerStudentForCourses(coursesIds, theStudents);
        List<Student> registeredStudents = new ArrayList<>();
        if (response !=null) {
            for (Student s : students) {
                s.registerStudentForCourses(coursesIds);
                registeredStudents.add(s);
            }
        }
        return studentRepository.saveAll(registeredStudents);
    }


    private Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format(FeedBackMessage.NOT_FOUND, id)));
    }
}
