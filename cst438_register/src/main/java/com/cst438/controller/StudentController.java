package com.cst438.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://registerf-cst438.herokuapp.com/"})

public class StudentController {
	@Autowired
	StudentRepository studentRepository;

	@PostMapping("/student")
	@Transactional
	public StudentDTO creatnewStudent( @RequestBody StudentDTO newStudent) {
		
		Student student = studentRepository.findByEmail(newStudent.email);
				
		// student.status
		// = 0  okay to register
		// != 0 hold on registration.  student.status may have reason for hold.
		
		if (student == null) {
			// TODO check that today's date is not past add deadline for the course.
			Student student1 = new Student();
			student1.setEmail(newStudent.email);
			student1.setName(newStudent.name);
			
			Student savedStudent = studentRepository.save(student1);
						
			StudentDTO result = createStudentDTO(savedStudent);
			return result;
      
		} 
		else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student with email already exist.");
		}	
	}
		
	private StudentDTO createStudentDTO(Student e) {
		StudentDTO student1DTO = new StudentDTO();
		student1DTO.Student_id = e.getStudent_id();
		student1DTO.email = e.getEmail();
		student1DTO.name = e.getName();
		student1DTO.Status = e.getStatus();

		student1DTO.Status_code = e.getStatusCode();
		
		return student1DTO;
	}
	
	@PatchMapping("/student/{email}")
	@Transactional
	public StudentDTO createStudentHold(@PathVariable String email) {
		
		Student studentHold = studentRepository.findByEmail(email);
	
		// check if the student has an hold
		if (studentHold.getStatusCode() == 0){
			studentHold.setStatusCode(2);	
		}
		
		else {
			studentHold.setStatusCode(0);	
		}
		
		Student savedStudent = studentRepository.save(studentHold);
		
		StudentDTO result = createStudentDTO(savedStudent);
		return result;		
	}
}
