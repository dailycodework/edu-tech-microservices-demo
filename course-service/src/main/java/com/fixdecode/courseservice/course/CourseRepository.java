package com.fixdecode.courseservice.course;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
public interface CourseRepository extends JpaRepository<Course, String> {

}
