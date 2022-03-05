package com.fixdecode.studentservice.student;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, String> {
}
