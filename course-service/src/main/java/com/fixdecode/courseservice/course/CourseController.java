package com.fixdecode.courseservice.course;


import com.fixdecode.courseservice.util.UrlMapping;
import com.fixdecode.courseservice.vo.VOTemplate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping(UrlMapping.COURSES)
public class CourseController {
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(){
        List<Course> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course theCourse){
        return new ResponseEntity<>(courseService.addCourse(theCourse),HttpStatus.CREATED);
    }

    @GetMapping(UrlMapping.ID)
    public ResponseEntity<Course> getCourse(@PathVariable("id") String id){
        return new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
    }

/*    @GetMapping(UrlMapping.GET_COURSE_WITH_STUDENTS_AND_INSTRUCTOR)
    ResponseEntity<VOTemplate> getCourseWithStudentsAndInstructor(@PathVariable("id") String courseId){
       var course = courseService.getCourseWithStudentsAndInstructor(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }*/

    @PostMapping(UrlMapping.ADD_COURSE_INSTRUCTOR)
    public ResponseEntity<Course> addCourseInstructor(
                                 @RequestParam("courseId") String courseId,
                                  @RequestParam("instructorId") String instructorId){
        return new ResponseEntity<>(
                courseService.addCourseInstructor(courseId, instructorId), HttpStatus.OK);
    }

    @PostMapping(UrlMapping.ADD_SELECTED_STUDENTS_TO_COURSES)
    public ResponseEntity<List<Course>> addSelectedStudentsToCourses(
                       @RequestParam("coursesIds") Set<String> coursesIds,
                       @RequestParam("studentsIds") Set<String> studentsIds){
        return new ResponseEntity<>(
                courseService.addSelectedStudentsToCourses(coursesIds, studentsIds), HttpStatus.OK);
    }
}
