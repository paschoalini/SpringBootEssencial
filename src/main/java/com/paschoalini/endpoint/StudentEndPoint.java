package com.paschoalini.endpoint;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paschoalini.error.ResourceNotFoundException;
import com.paschoalini.model.Student;
import com.paschoalini.repository.StudentRepository;

@RestController
@RequestMapping("students")
public class StudentEndPoint {
	private final StudentRepository studentDAO;

	@Autowired
	public StudentEndPoint(StudentRepository studentDAO) {
		this.studentDAO = studentDAO;
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/findStudentByName/{name}")
	public ResponseEntity<?> getStudentByName(@PathVariable String name) {
		return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
	}

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student) {
		/*
		studentDAO.save(student);
		student.setId(null);
		studentDAO.save(student);
		if(true)
			throw new RuntimeException("Test transaction");
		studentDAO.save(student);
		
		Teste para aprender a definir o InnoDB como engine do banco, permitindo uso de transações
		e garantindo que em caso de erro, o que foi salvo/alterado voltará ao estado original.
		*/
		return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		verifyIfStudentExists(id);
		studentDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping
	public void delete(@Valid @RequestBody Student student) {
		delete(student.getId());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Student student) {
		verifyIfStudentExists(student.getId());
		studentDAO.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
		verifyIfStudentExists(id);
		Optional<Student> student = studentDAO.findById(id);
		return new ResponseEntity<>(student.get(), HttpStatus.OK);
	}


	private void verifyIfStudentExists(Long id) {
		if (!studentDAO.findById(id).isPresent())
			throw new ResourceNotFoundException("Student not found for ID: " + id);
	}
}
