package com.pwd.tokolapak.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwd.tokolapak.entity.Department;
import com.pwd.tokolapak.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	public Iterable<Employee> findByDepartment(Department department);
}
