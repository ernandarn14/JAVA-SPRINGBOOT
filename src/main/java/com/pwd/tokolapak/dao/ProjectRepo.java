package com.pwd.tokolapak.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwd.tokolapak.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

}
