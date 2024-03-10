package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.model.Project;
import com.example.service.ProjectService;

@ExtendWith(SpringExtension.class)
class ProjectControllerTest {

	@Mock
	private ProjectService projectService;

	@InjectMocks
	private ProjectController projectController;

	@Test
	public void createProject() {
		Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));
		ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
		when(projectController.createProject(project)).thenReturn(responseEntity);
		ResponseEntity responseEntity1 = projectService.createProject(project);
		assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getAllProjects() {
		ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
		when(projectService.getAllProjects()).thenReturn(responseEntity);
		ResponseEntity responseEntity1 = projectController.getAllProjects();
		assertEquals(responseEntity1.getStatusCode(), responseEntity.getStatusCode());
	}

	@Test
	public void updateProject() {
		Long id= 1L;
		Project project = new Project(id, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));
		ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
		when(projectService.updateProject(id, project)).thenReturn(responseEntity);
		ResponseEntity responseEntity1 = projectController.updateProject(id, project);
		assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
    public void deleteProject() {

        Long id=1L;

        ResponseEntity responseEntity=new ResponseEntity<>(HttpStatus.OK);
        when(projectService.deleteProject(id)).thenReturn(responseEntity);
        ResponseEntity responseEntity1= projectController.deleteProject(id);
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }
	
	@Test
	public void testGetProjectById() {
	    Long id = 1L;
	    Project project = new Project(id, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
	            LocalDate.of(2024, 6, 30));
	    ResponseEntity responseEntity = new ResponseEntity<>(project, HttpStatus.OK);
	    when(projectService.getProjectById(id)).thenReturn(responseEntity);
	    ResponseEntity responseEntity1 = projectController.getProjectById(id);
	    assertEquals(responseEntity.getStatusCode(), responseEntity1.getStatusCode());
	    assertEquals(responseEntity.getBody(), responseEntity1.getBody());
	}

}
