package com.pwd.tokolapak.service;

import com.pwd.tokolapak.entity.Department;

public interface DepartmentService {
	public Iterable<Department> getDepartment();
	
	public Department addDepartment(Department department);
}
