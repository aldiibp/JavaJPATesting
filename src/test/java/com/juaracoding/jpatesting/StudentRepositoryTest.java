package com.juaracoding.jpatesting;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.juaracoding.jpatesting.model.Student;
import com.juaracoding.jpatesting.repository.StudentRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveStudentTesting() {
		Student student = Student.builder()
				.firstName("Test 1")
				.lastName("Test 1")
				.email("test1@gmail.com")
				.build();
		studentRepository.save(student);
		Assertions.assertThat(student.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	public void getStudentTest() {
		Student student = studentRepository.findById(1L).get();
		Assertions.assertThat(student.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	@Rollback(value = false)
	public void getListOfStudentTest() {
		List<Student> student = studentRepository.findAll();
		Assertions.assertThat(student.size()).isEqualTo(1L);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateStudentTest() {
		Student student = studentRepository.findById(1L).get();
		student.setEmail("test11@gmail.com");
		
		Student studentUpdate = studentRepository.save(student);
		Assertions.assertThat(studentUpdate.getEmail()).isEqualTo("test11@gmail.com");		
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteStudentTest() {
		Student student = studentRepository.findById(1L).get();
		studentRepository.delete(student);
		Student student1 = null;
		Optional<Student> optionalStudent = studentRepository.findByEmail("test11@gmail.com");
		if(optionalStudent.isPresent()) {
			student1 = optionalStudent.get();
		}
		Assertions.assertThat(student1).isNull();
	}
}
