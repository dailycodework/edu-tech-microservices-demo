package com.fixdecode.courseservice.course;

import com.fixdecode.courseservice.exceptions.CourseNotFoundException;
import com.fixdecode.courseservice.util.FeedBackMessage;
import com.fixdecode.courseservice.vo.Instructor;
import com.fixdecode.courseservice.vo.RequestTemplate;
import com.fixdecode.courseservice.vo.VOTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public VOTemplate getCourseWithStudentsAndInstructor(String courseId){
        VOTemplate VOT = new VOTemplate();
        Course theCourse = this.getCourse(courseId);
        Object[] students = requestTemplate.getCourseStudents(theCourse.getStudentsIds());
        Instructor instructor = requestTemplate.getCourseInstructor(theCourse.getInstructorId());
        VOT.setInstructor(instructor);
        VOT.setStudents(students);
        return VOT;
    }
}
