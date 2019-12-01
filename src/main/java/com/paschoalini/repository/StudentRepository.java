package com.paschoalini.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.paschoalini.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	/**
	 * Returns all instances of the type Student with the given name or part of it.
	 * <p>
	 * If some or all names are not found, no entities are returned for these names.
	 * <p>
	 * Note that the order of elements in the result is not guaranteed.
	 *
	 * @param name full or part of students name.
	 * @return guaranteed to be not {@literal null}. The size can be equal or less than the number of given
	 *         {@literal ids}.
	 *         
	 */
	List<Student> findByNameIgnoreCaseContaining(String name);
}
