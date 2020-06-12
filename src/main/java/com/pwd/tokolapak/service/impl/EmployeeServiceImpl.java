package com.pwd.tokolapak.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwd.tokolapak.dao.EmployeeAddressRepo;
import com.pwd.tokolapak.dao.EmployeeRepo;
import com.pwd.tokolapak.entity.Employee;
import com.pwd.tokolapak.entity.EmployeeAddress;
import com.pwd.tokolapak.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private EmployeeAddressRepo employeeAddressRepo;

	@Override
	@Transactional
	public Iterable<Employee> getEmployee() {
		// TODO Auto-generated method stub
		return employeeRepo.findAll();
	}

	@Override
	@Transactional
	public Optional<Employee> getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return employeeRepo.findById(id);
	}

	@Override
	@Transactional
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employee.setId(0);
		return employeeRepo.save(employee);
	}

	@Override
	@Transactional
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub2
		Optional<Employee> findEmployee = employeeRepo.findById(employee.getId());
		
		if (findEmployee.get() == null)
			throw new RuntimeException("Employee Not Found");
		
		return employeeRepo.save(employee);
	}
	
	@Override
	@Transactional
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		Optional<Employee> findEmployee = employeeRepo.findById(id);
		if(findEmployee.toString() == "Optional.empty")
			throw new RuntimeException("Employee with id " + id + " doesn't exist");
		employeeRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Iterable<EmployeeAddress> getEmployeeAddress() {
		// TODO Auto-generated method stub
		return employeeAddressRepo.findAll();
	}

	@Override
	@Transactional
	public Optional<EmployeeAddress> getEmployeeAddressById(int id) {
		// TODO Auto-generated method stub
		return employeeAddressRepo.findById(id);
	}
	
	@Override
	@Transactional
	public void deleteEmployeeAddress(EmployeeAddress employeeAddress) {
		// TODO Auto-generated method stub
		employeeAddress.getEmployee().setEmployeeAddress(null); //cara hapus relation employee ke address
		employeeAddress.setEmployee(null); // hapus relation address ke employee
		employeeAddressRepo.delete(employeeAddress);
	}

	
	@Override
	@Transactional
	public Employee updateNullEmployeeAddress(Employee employee) {
		// TODO Auto-generated method stub
		employee.getEmployeeAddress();
		
		if (employee.getEmployeeAddress() == null)
			employeeRepo.save(employee);
		
		return employeeRepo.save(employee);
	}


	@Override
	@Transactional
	public EmployeeAddress addAddress(EmployeeAddress employeeAddress) {
		employeeAddress.setId(0);
		return employeeAddressRepo.save(employeeAddress);
	}

}
