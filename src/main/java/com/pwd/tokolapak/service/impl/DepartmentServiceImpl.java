package com.pwd.tokolapak.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwd.tokolapak.dao.DepartmentRepo;
import com.pwd.tokolapak.entity.Department;
import com.pwd.tokolapak.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepo departmentRepo;

	@Override
	@Transactional
	public Iterable<Department> getDepartment() {
		// TODO Auto-generated method stub
		return departmentRepo.findAll();
	}

	@Override
	@Transactional
	public Department addDepartment(Department department) {
		// TODO Auto-generated method stub
		department.setId(0);
		return departmentRepo.save(department);
	}
	
	

}
