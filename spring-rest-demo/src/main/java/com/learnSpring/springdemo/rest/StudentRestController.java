package com.learnSpring.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnSpring.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> students;
	
	// define @PostConstruct to load the student data ... only once
	// runs when the program start
	@PostConstruct
	public void loadData() {
		students = new ArrayList<Student>();
		
		students.add(new Student("mohamed", "ashraf"));
		students.add(new Student("ahmed", "omar"));
		students.add(new Student("kareem", "momen"));
	}
	
	// define endpoint for "/students" - return a list of students
	@GetMapping("/students")
	public List<Student> getStudents(){
	
		return students;
	}
	
	// define endpoint for "/students" - return a list of students
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		// check the studentId against list size
		if(studentId >= students.size() || studentId < 0) {
			throw new StudentNotFoundException("Student id not found -" + studentId);
		}
		
		return students.get(studentId);
	}

}

