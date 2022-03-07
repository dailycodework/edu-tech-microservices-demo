package com.fixdecode.courseservice.util;

public final class UrlMapping {
    public static final String API ="api/";
    public static final String COURSES = API+"courses";
    public static final String ID ="/{id}";
    public static final String GET_COURSE_WITH_STUDENTS_AND_INSTRUCTOR ="/course/{id}" ;
    public static final String ADD_COURSE_INSTRUCTOR ="/course/add-course-instructor" ;
    public static final String ADD_SELECTED_STUDENTS_TO_COURSES ="/add-students-courses" ;
}
