package com.nebulaops.controller;
import com.nebulaops.dto.*;
import com.nebulaops.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    public ProjectController(ProjectService projectService) { this.projectService = projectService; }
    @PostMapping public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request, Authentication authentication) { return ResponseEntity.ok(projectService.createProject(request, authentication.getName())); }
    @GetMapping public ResponseEntity<List<ProjectResponse>> getUserProjects(Authentication authentication) { return ResponseEntity.ok(projectService.getUserProjects(authentication.getName())); }
    @GetMapping("/{id}") public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id, Authentication authentication) { return ResponseEntity.ok(projectService.getProjectById(id, authentication.getName())); }
}
