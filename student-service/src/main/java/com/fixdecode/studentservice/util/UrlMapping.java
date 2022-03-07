package com.fixdecode.studentservice.util;

public final class UrlMapping {
    //Mapping all endpoints urls
    public static final String API ="api/v1/";
    public static final String STUDENTS = API+"students";
    public static final String GET_STUDENT_BY_ID = "/{id}";
    public static final String UPDATE_STUDENT ="/update" ;
    public static final String GET_SELECTED_STUDENT_BY_ID = "/student/selected/{ids}";
    public static final String REGISTER_STUDENTS_FOR_COURSES ="/register-student-courses" ;

}
