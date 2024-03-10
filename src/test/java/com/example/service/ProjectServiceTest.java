package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.model.Project;
import com.example.repository.ProjectRepository;

@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

	@Mock
	private ProjectRepository projectRepository;

	@InjectMocks
	private ProjectService projectService;

    @Test
    void createProject() {
		Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));
		when(projectRepository.save(project)).thenReturn(project);
		ResponseEntity<?> entity = projectService.createProject(project);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
	}

    @Test
    void addProjectException() {

		Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));

		when(projectRepository.save(project)).thenThrow(RuntimeException.class);
		ResponseEntity<?> responseEntity = projectService.createProject(project);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

    @Test
    void getAllProjects() {

		List<Project> projectList = new ArrayList<>();
		projectList.add(
				new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15), LocalDate.of(2024, 6, 30)));

		when(projectRepository.findAll()).thenReturn(projectList);
		ResponseEntity<?> responseEntity = projectService.getAllProjects();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

    @Test
    void getAllProjectException() {

		when(projectRepository.findAll()).thenThrow(RuntimeException.class);
		ResponseEntity<?> responseEntity = projectService.getAllProjects();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

    @Test
    void getAllProjectEmpty() {

		when(projectRepository.findAll()).thenReturn(Collections.emptyList());
		ResponseEntity<?> responseEntity = projectService.getAllProjects();
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

    @Test
    void getProjectById() {
	    long id = 1;
	    Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
	            LocalDate.of(2024, 6, 30));
	    when(projectRepository.findById(id)).thenReturn(Optional.of(project));
	    ResponseEntity<?> responseEntity = projectService.getProjectById(id);
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

    @Test
    void getProjectByIdNotFound() {
	    long id = 1;
	    when(projectRepository.findById(id)).thenReturn(Optional.empty());
	    ResponseEntity<?> responseEntity = projectService.getProjectById(id);
	    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

    @Test
    void getProjectByIdException() {
	    long id = 1;
	    when(projectRepository.findById(id)).thenThrow(RuntimeException.class);
	    ResponseEntity<?> responseEntity = projectService.getProjectById(id);
	    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

    @Test
    void updateProjectSuccess() {
		long id = 1;
		Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));
		when(projectRepository.findById(id)).thenReturn(Optional.of(project));
		ResponseEntity<?> responseEntity = projectService.updateProject(id, project);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

    @Test
    void updateProjectNotFound() {
		long id = 1;
		Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));
		when(projectRepository.findById(id)).thenReturn(Optional.empty());
		ResponseEntity<?> responseEntity = projectService.updateProject(id, project);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

    @Test
    void updateProjectException() {

		long id = 1;
		Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));
		when(projectRepository.findById(id)).thenThrow(RuntimeException.class);
		ResponseEntity<?> responseEntity = projectService.updateProject(id, project);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

    @Test
    void deleteProjectSuccess() {
		long id = 1;
		Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));

		when(projectRepository.findById(id)).thenReturn(Optional.of(project));
		ResponseEntity<?> responseEntity = projectService.deleteProject(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

    @Test
    void deleteProjectNotFound() {
		long id = 1;

		when(projectRepository.findById(id)).thenReturn(Optional.empty());
		ResponseEntity<?> responseEntity = projectService.deleteProject(id);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

    @Test
    void deleteProjectException() {
		long id = 1;
		Project project = new Project(1L, "Project 1", "Description 1", LocalDate.of(2024, 3, 15),
				LocalDate.of(2024, 6, 30));

		when(projectRepository.findById(id)).thenThrow(RuntimeException.class);
		ResponseEntity<?> responseEntity = projectService.deleteProject(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}
}
