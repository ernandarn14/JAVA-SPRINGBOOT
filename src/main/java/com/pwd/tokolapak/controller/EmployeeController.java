package com.pwd.tokolapak.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pwd.tokolapak.dao.DepartmentRepo;
import com.pwd.tokolapak.dao.EmployeeAddressRepo;
import com.pwd.tokolapak.dao.EmployeeRepo;
import com.pwd.tokolapak.dao.ProjectRepo;
import com.pwd.tokolapak.entity.Department;
import com.pwd.tokolapak.entity.Employee;
import com.pwd.tokolapak.entity.EmployeeAddress;
import com.pwd.tokolapak.entity.Project;
import com.pwd.tokolapak.service.EmployeeService;

@RestController
@RequestMapping("/employee")
//@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeAddressRepo employeeAddressRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@PostMapping//localhost:8080/employee/add
	public Employee addEmployee(@RequestBody Employee employee) {
//		return employeeRepo.save(employee);
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping
	public Iterable<Employee> getEmployee() {
//		return employeeRepo.findAll();
		return employeeService.getEmployee();
	}
	
	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable int id) {
//		return employeeRepo.findAll();
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/address")
	public Iterable<EmployeeAddress> getEmployeeAddress(){
//		return employeeAddressRepo.findAll();
		return employeeService.getEmployeeAddress();
	}
	
	@GetMapping("/address/{id}")
	public Optional<EmployeeAddress> getEmployeeAddressById(@PathVariable int id){
		return employeeService.getEmployeeAddressById(id);
	}
	
	@PutMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("{id}")
	public void deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
	}
	
	@PostMapping("/address")
	public EmployeeAddress addAddress(EmployeeAddress employeeAddress) {
		return employeeService.addAddress(employeeAddress);
	}
	
	
//	@PutMapping("{id}")
//	public Employee updateNullEmployeeAddress(@RequestBody Employee employee) { 
//		 return employeeService.updateNullEmployeeAddress(employee);
//	}
	
	@PutMapping("{employeeId}/address/{addressId}")
	public Employee updateNullEmployeeAddress(@PathVariable int addressId, @PathVariable int employeeId) {
		return employeeService.updateNullEmployeeAddress(addressId, employeeId);
	}
	
	@DeleteMapping("/address/{id}")
	public void deleteEmployeeAddresById(@PathVariable int id) {
//		EmployeeAddress employeeAddress;
		Optional<EmployeeAddress> employeeAddress = employeeAddressRepo.findById(id);
		
		if (employeeAddress.get() == null)
			throw new RuntimeException("Employee Address Not Found");
		
		employeeService.deleteEmployeeAddress(employeeAddress.get());
	}
	
	@PutMapping("{employeeId}/department/{departmentId}")
	public Employee addEmployeeDepartment(@PathVariable int departmentId, @PathVariable int employeeId ) {
		Employee findEmployee = employeeRepo.findById(employeeId).get();
		
		if (findEmployee == null)
			throw new RuntimeException("Employee Not Found");
		
		Department findDepartment = departmentRepo.findById(departmentId).get();
		
		if (findDepartment == null)
			throw new RuntimeException("Department Not Found");
		
		findEmployee.setDepartment(findDepartment);
		return employeeRepo.save(findEmployee);
		
//		return departmentRepo.findById(departmentId).map(department -> {
//			findEmployee.setDepartment(department);
//			return employeeRepo.save(findEmployee);
//		}).orElseThrow(() -> new RuntimeException("Department Not Found"));
	}
	
	@PostMapping("/department/{departmentId}")
	public Employee addEmployee(@PathVariable int departmentId, @RequestBody Employee employee ) {
		Department findDepartment = departmentRepo.findById(departmentId).get();
		
		if (findDepartment == null)
			throw new RuntimeException("Department Not Found");
		
		employee.setDepartment(findDepartment);
		return employeeRepo.save(employee);
	}
	
	@PostMapping("{employeeId}/projects/{projectId}")
	public Employee addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
		Employee findEmployee = employeeRepo.findById(employeeId).get();
		
		Project findProject = projectRepo.findById(projectId).get();
		
		findEmployee.getProject().add(findProject);
		
		return employeeRepo.save(findEmployee);
	}
	
	
	
}
