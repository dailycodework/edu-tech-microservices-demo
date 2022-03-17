package com.fixdecode.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMessageController {

    @GetMapping("/studentFallBackMessage")
    public String studentFallBack(){
        return "Student Service is taking a long time, please try again later";
    }

    @GetMapping("/courseFallBackMessage")
    public String courseFallBack(){
        return "Course Service is taking a long time, please try again later";
    }

    @GetMapping("/instructorFallBackMessage")
    public String instructorFallBack(){
        return "Instructor Service is taking a long time, please try again later";
    }
}
