package com.fixdecode.studentservice.student;

import com.fixdecode.studentservice.exceptions.StudentNotFoundException;
import com.fixdecode.studentservice.util.FeedBackMessage;
import com.fixdecode.studentservice.vo.RequestTemplate;
import com.fixdecode.studentservice.vo.VOTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

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

    public Student getStudent(String email){
        return studentRepository.findById(email)
                .orElseThrow(() -> new StudentNotFoundException(String.format(FeedBackMessage.NOT_FOUND, email)));
    }

    public VOTemplate getStudentWithCourses(String studentEmail){
        VOTemplate VOT = new VOTemplate();
        var theStudent =  this.getStudent(studentEmail);
        Object[] courses = requestTemplate.getStudentCourses(theStudent.getCoursesId());
        VOT.setStudent(theStudent);
        VOT.setCourses(courses);
        return VOT;
    }

    public List<Student> getSelectedStudents(Iterable<String> studentIds) {
        return studentRepository.findAllById(studentIds);
    }
}
