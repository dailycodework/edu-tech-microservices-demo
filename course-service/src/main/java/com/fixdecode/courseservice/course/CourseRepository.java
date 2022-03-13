package com.fixdecode.courseservice.course;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CourseRepository extends JpaRepository<Course, String> {
}
