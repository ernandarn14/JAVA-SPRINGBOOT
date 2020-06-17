package com.pwd.tokolapak.service;
import java.util.Optional;

import com.pwd.tokolapak.entity.Employee;
import com.pwd.tokolapak.entity.EmployeeAddress;

public interface EmployeeService {
	
	public Iterable<Employee> getEmployee();
	public Optional<Employee> getEmployeeById(int id);
	public Employee addEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
//	public Employee updateNullEmployeeAddress(Employee employee);
	public Employee updateNullEmployeeAddress(int addressId, int employeeId);
	public void deleteEmployee(int id);
	
	public Iterable<EmployeeAddress> getEmployeeAddress();
	public Optional<EmployeeAddress> getEmployeeAddressById(int id);
	public EmployeeAddress addAddress(EmployeeAddress employeeAddress);
	public void deleteEmployeeAddress(EmployeeAddress employeeAddress);
}
