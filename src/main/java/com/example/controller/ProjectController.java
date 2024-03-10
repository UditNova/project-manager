package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Project;
import com.example.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("app/v1/project")
public class ProjectController {
	
	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	// create a new project
	@Operation(summary = "Create a new project")
    @PostMapping("/create")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project){
		return projectService.createProject(project);
	}
	
	// get all projects
	@Operation(summary = "Get all projects")
	@GetMapping()
	public ResponseEntity<?> getAllProjects(){
		return projectService.getAllProjects();
	}
	
	// get project by ID
	@Operation(summary = "Get project by ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Found the project"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
	@GetMapping("/{id}")
	public ResponseEntity<?> getProjectById(@PathVariable Long id){
		return projectService.getProjectById(id);
	}
	
	// update project
	@Operation(summary = "Update project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the project"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody Project project){
		return projectService.updateProject(id, project);
	}
	
	// delete project by ID
	@Operation(summary = "Delete project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the project"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id){
		return projectService.deleteProject(id);
	}
}
