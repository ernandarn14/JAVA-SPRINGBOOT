package com.pwd.tokolapak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pwd.tokolapak.dao.ProjectRepo;
import com.pwd.tokolapak.entity.Employee;
import com.pwd.tokolapak.entity.Project;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@GetMapping
	public Iterable<Project> getProjects() {
		return projectRepo.findAll();
	}
	
	@GetMapping("/{projectId}")
	public Project getProjectById(@PathVariable int projectId) {
		Project findProject = projectRepo.findById(projectId).get();
		
		if (findProject == null)
			throw new RuntimeException("Project Not Found");
		
		return findProject;	
	}
	
	@PostMapping
	public Project addProject(@RequestBody Project project) {
		project.setId(0);
		return projectRepo.save(project);
	}
	
	@GetMapping("/{projectId}/employees")
	public List<Employee> getEmployeeProject(@PathVariable int projectId){
		Project findProject = projectRepo.findById(projectId).get();
		
		return findProject.getEmployees();
	}
}
