package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.model.Error;
import com.example.model.Project;
import com.example.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	// Create operation
	public ResponseEntity<?> createProject(Project project) {
		
		try {
			Project createdProject = projectRepository.save(project);
			return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			Error error=Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR
					.getReasonPhrase())
					.message("Adding project failed").build();
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Read operation
	public ResponseEntity<?> getAllProjects() {
		
		try {
			
			List<Project> projects = projectRepository.findAll();
			if(projects.isEmpty()) {
				Error error= Error.builder().code(HttpStatus.NOT_FOUND
						.getReasonPhrase())
						.message("Sorry, there is no projects.").build();
				return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(projects, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Error error= Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR
					.getReasonPhrase())
					.message("Fetching of projects failed! ").build();
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 public ResponseEntity<?> getProjectById(Long id) {
	        try {
	            Optional<Project> projectOptional = projectRepository.findById(id);
	            if (projectOptional.isEmpty()) {
	                Error error = Error.builder()
	                        .code(HttpStatus.NOT_FOUND.getReasonPhrase())
	                        .message("Project not found.")
	                        .build();
	                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	            }
	            Project project = projectOptional.get();
	            return new ResponseEntity<>(project, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            Error error = Error.builder()
	                    .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
	                    .message("Failed to retrieve project.")
	                    .build();
	            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	// Update operation
	public ResponseEntity<?> updateProject(Long id, Project project) {
		try {
			
			Optional<Project> oldProject = projectRepository.findById(id);
			if (oldProject.isEmpty()) {
				Error error= Error.builder().code(HttpStatus.NOT_FOUND
						.getReasonPhrase())
						.message("Sorry, project not found.").build();
				return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			}
			Project updatedProject= oldProject.get();
			updatedProject.setId(id);
			updatedProject.setName(project.getName());
			updatedProject.setDescription(project.getDescription());
			updatedProject.setStartDate(project.getStartDate());
			updatedProject.setEndDate(project.getEndDate());
			Project savedProject = projectRepository.save(updatedProject);
			return new ResponseEntity<>(savedProject, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Error error= Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR
					.getReasonPhrase())
					.message("Updation proccess failed!").build();
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Delete operation
	public ResponseEntity<?> deleteProject(Long id) {
		
		try {
			
			Optional<Project> projectById = projectRepository.findById(id);
			
			if (projectById.isEmpty()) {
				Error error=Error.builder().code(HttpStatus.NOT_FOUND
						.getReasonPhrase())
						.message("Sorry, there is no project of this id.").build();
				return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			}
			projectRepository.deleteById(id);
			Error error = Error.builder().code(HttpStatus.OK.getReasonPhrase())
					.message("Project deleted successfully").build();
			return new ResponseEntity<>(error, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Error error= Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR
					.getReasonPhrase())
					.message("Deletion of user failed.").build();
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
