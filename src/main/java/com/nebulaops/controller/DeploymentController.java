package com.nebulaops.controller;
import com.nebulaops.dto.*;
import com.nebulaops.service.DeploymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deployments")
public class DeploymentController {
    private final DeploymentService deploymentService;
    public DeploymentController(DeploymentService deploymentService) { this.deploymentService = deploymentService; }
    @PostMapping public ResponseEntity<DeploymentResponse> triggerDeployment(@Valid @RequestBody DeploymentRequest request, Authentication authentication) { return ResponseEntity.ok(deploymentService.triggerDeployment(request, authentication.getName())); }
    @GetMapping("/project/{projectId}") public ResponseEntity<List<DeploymentResponse>> getProjectDeployments(@PathVariable Long projectId, Authentication authentication) { return ResponseEntity.ok(deploymentService.getProjectDeployments(projectId, authentication.getName())); }
}
