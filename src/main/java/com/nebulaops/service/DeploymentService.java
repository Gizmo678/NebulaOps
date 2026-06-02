package com.nebulaops.service;
import com.nebulaops.domain.*;
import com.nebulaops.dto.*;
import com.nebulaops.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeploymentService {
    private static final Logger log = LoggerFactory.getLogger(DeploymentService.class);
    private final DeploymentRepository deploymentRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public DeploymentService(DeploymentRepository deploymentRepository, ProjectRepository projectRepository, UserRepository userRepository, NotificationService notificationService) {
        this.deploymentRepository = deploymentRepository; this.projectRepository = projectRepository; this.userRepository = userRepository; this.notificationService = notificationService;
    }

    @Transactional
    public DeploymentResponse triggerDeployment(DeploymentRequest request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Project project = projectRepository.findById(request.getProjectId()).orElseThrow(() -> new RuntimeException("Project not found"));
        if (!project.getOwner().getId().equals(user.getId())) throw new RuntimeException("Unauthorized");
        Deployment deployment = new Deployment(project, user);
        deployment = deploymentRepository.save(deployment);
        log.info("Started deployment {} for project {}", deployment.getId(), project.getName());
        simulateDeployment(deployment, project);
        return mapToResponse(deployment);
    }

    private void simulateDeployment(Deployment deployment, Project project) {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                deployment.setStatus(DeploymentStatus.SUCCESS);
                deployment.setCompletedAt(LocalDateTime.now());
                deployment.setDeploymentLogs("Build successful. Deployed to " + project.getCloudProvider());
                deploymentRepository.save(deployment);
                notificationService.sendDeploymentSuccessAlert(project.getName(), project.getEnvironment());
            } catch (Exception e) {
                deployment.setStatus(DeploymentStatus.FAILED);
                deployment.setCompletedAt(LocalDateTime.now());
                deployment.setDeploymentLogs("Error: " + e.getMessage());
                deploymentRepository.save(deployment);
                notificationService.sendDeploymentFailureAlert(project.getName(), project.getEnvironment(), e.getMessage());
            }
        }).start();
    }

    public List<DeploymentResponse> getProjectDeployments(Long projectId, String username) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        if (!project.getOwner().getUsername().equals(username)) throw new RuntimeException("Unauthorized");
        return deploymentRepository.findByProjectId(projectId).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private DeploymentResponse mapToResponse(Deployment deployment) {
        return new DeploymentResponse(deployment.getId(), deployment.getProject().getId(), deployment.getTriggeredBy().getUsername(), deployment.getStatus(), deployment.getStartedAt(), deployment.getCompletedAt());
    }
}
