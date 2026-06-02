package com.nebulaops.service;
import com.nebulaops.domain.*;
import com.nebulaops.dto.*;
import com.nebulaops.repository.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository; this.userRepository = userRepository;
    }

    @CacheEvict(value = "userProjects", key = "#username")
    public ProjectResponse createProject(ProjectRequest request, String username) {
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Project project = new Project(request.getName(), request.getDescription(), request.getGithubRepository(), request.getCloudProvider(), request.getEnvironment(), owner);
        project = projectRepository.save(project);
        return mapToResponse(project);
    }

    @Cacheable(value = "userProjects", key = "#username")
    public List<ProjectResponse> getUserProjects(String username) {
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return projectRepository.findByOwnerId(owner.getId()).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public ProjectResponse getProjectById(Long id, String username) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        if (!project.getOwner().getUsername().equals(username)) throw new RuntimeException("Unauthorized");
        return mapToResponse(project);
    }

    private ProjectResponse mapToResponse(Project project) {
        return new ProjectResponse(project.getId(), project.getName(), project.getDescription(), project.getGithubRepository(), project.getCloudProvider(), project.getEnvironment(), project.getOwner().getUsername(), project.getCreatedAt());
    }
}
