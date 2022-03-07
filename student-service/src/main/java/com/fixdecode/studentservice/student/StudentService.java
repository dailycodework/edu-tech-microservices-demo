package com.fixdecode.studentservice.student;

import com.fixdecode.studentservice.exceptions.StudentNotFoundException;
import com.fixdecode.studentservice.util.FeedBackMessage;
import com.fixdecode.studentservice.vo.RequestTemplate;
import com.fixdecode.studentservice.vo.VOTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public VOTemplate getStudentWithCourses(String studentEmail){
        VOTemplate VOT = new VOTemplate();
        var theStudent =  this.getStudentById(studentEmail);
        Object[] courses = requestTemplate.getStudentCourses(theStudent.getCoursesId());
        VOT.setStudent(theStudent);
        VOT.setCourses(courses);
        return VOT;
    }

    public List<Student> getSelectedStudents(Iterable<String> studentIds) {
        return studentRepository.findAllById(studentIds);
    }

    public Student updateStudent(Student theStudent) {
        var student = this.getStudentById(theStudent.getId());
        theStudent.setCoursesId(student.getCoursesId());
        return studentRepository.save(theStudent);
    }

    @Transactional
    public List<Student> registerStudents(Set<String> studentsIds, Set<String> coursesIds) {
        var students = getSelectedStudents(studentsIds);
        List<Student> registeredStudents = new ArrayList<>();
        for (Student s : students){
            s.registerStudentForCourses(coursesIds);
            registeredStudents.add(s);
        }
        return studentRepository.saveAll(registeredStudents);
    }

    private Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format(FeedBackMessage.NOT_FOUND, id)));
    }
}
