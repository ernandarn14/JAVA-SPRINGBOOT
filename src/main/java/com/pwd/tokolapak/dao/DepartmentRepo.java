package com.pwd.tokolapak.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwd.tokolapak.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	public Department findByName(String name);
}
