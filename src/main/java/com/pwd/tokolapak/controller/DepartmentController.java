package com.pwd.tokolapak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pwd.tokolapak.dao.DepartmentRepo;
import com.pwd.tokolapak.dao.EmployeeRepo;
import com.pwd.tokolapak.entity.Department;
import com.pwd.tokolapak.service.DepartmentService;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@GetMapping
	public Iterable<Department> getDepartment(){
		return departmentService.getDepartment();
	}
	
	@PostMapping
	public Department addDepartment(@RequestBody Department department) {
		return departmentService.addDepartment(department);
	}
	
	@DeleteMapping("/{departmentId}")
	public void deleteDepartment(@PathVariable int departmentId) {
		Department findDepartment = departmentRepo.findById(departmentId).get();
		
		findDepartment.getEmployees().forEach(employee -> {
			employee.setDepartment(null);
			employeeRepo.save(employee);
		});
	}
}
