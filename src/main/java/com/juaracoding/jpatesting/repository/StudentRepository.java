package com.juaracoding.jpatesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juaracoding.jpatesting.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Optional<Student> findByEmail(String email);
	
}
