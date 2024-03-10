package com.example.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(name = "Project-Manager")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the project")
    private Long id;
    
    @NotBlank(message = "Name can not be blank")
    @NotNull(message = "Name can not be null")
    @Size(max=100, min = 3, message = "Name length must be between 3 and 100 characters")
    @Schema(description = "The name of the project", required = true, example = "Project ABC")
    private String name;
    
    @NotNull(message = "Description can not be null")
    @NotBlank(message = "Description is required! ")
    @Schema(description = "The description of the project", required = true, example = "This is a description of the project")
    private String description;
    
    @Schema(description = "The start date of the project", example = "2023-01-01")
    private LocalDate startDate;
    
    @Schema(description = "The end date of the project", example = "2023-12-31")
    private LocalDate endDate;
}
