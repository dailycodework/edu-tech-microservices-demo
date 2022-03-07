package com.fixdecode.courseservice.course;

import com.fixdecode.courseservice.exceptions.CourseNotFoundException;
import com.fixdecode.courseservice.util.FeedBackMessage;
import com.fixdecode.courseservice.vo.Instructor;
import com.fixdecode.courseservice.vo.RequestTemplate;
import com.fixdecode.courseservice.vo.Student;
import com.fixdecode.courseservice.vo.VOTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;
    private RequestTemplate requestTemplate;

    public List<Course> getCourses(){
        return courseRepository.findAll().stream().toList();
    }

    public Course addCourse(Course theCourse){
        return courseRepository.save(theCourse);
    }

    public Course getCourse(String id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(String.format(FeedBackMessage.NOT_FOUND, id)));
    }

   /* @Transactional
    public VOTemplate getCourseWithStudentsAndInstructor(String courseId){
        VOTemplate VOT = new VOTemplate();
        Course theCourse = this.getCourse(courseId);
        //Make a rest call to get selected student from the student-service
        Object[] students = requestTemplate.getSelectedStudents(theCourse.getStudentsIds());
        log.info("Students Found  {} : ", Arrays.stream(students).toArray());
        //Make a rest call to instructor-service
        Instructor instructor = requestTemplate.getCourseInstructor(theCourse.getInstructorId());
        VOT.setInstructor(instructor);
        VOT.setStudents(students);
        return VOT;
    }*/

    public Course addCourseInstructor(String courseId, String instructorId) {
        var course = getCourse(courseId);
        Instructor instructor = requestTemplate.getCourseInstructor(instructorId);
        course.setInstructorId(instructor.getId());
        return courseRepository.save(course);
    }

    @Transactional
    public List<Course> addSelectedStudentsToCourses(Set<String> coursesIds, Set<String> studentsIds) {
        List<Course> studentToAdd = new ArrayList<>();
        var courses = courseRepository.findAllById(coursesIds);
        //Make a rest call to get selected student from the student-service
        Student[] students = requestTemplate.getSelectedStudents(studentsIds);
        log.info("Students Found {} : ", (Object) students);
        var studentIds = Arrays.stream(students).map(Student::getId).collect(toSet());
        for (Course course : courses){
            course.addStudentsToCourses(studentIds);
            studentToAdd.add(course);
        }
       return courseRepository.saveAll(studentToAdd);
    }
}
