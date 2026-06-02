package com.nebulaops.controller;
import com.nebulaops.domain.DeploymentStatus;
import com.nebulaops.repository.DeploymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {
    private final DeploymentRepository deploymentRepository;
    public MonitoringController(DeploymentRepository deploymentRepository) { this.deploymentRepository = deploymentRepository; }
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDeploymentStats() {
        long totalDeployments = deploymentRepository.count();
        long successfulDeployments = deploymentRepository.findAll().stream().filter(d -> d.getStatus() == DeploymentStatus.SUCCESS).count();
        long failedDeployments = deploymentRepository.findAll().stream().filter(d -> d.getStatus() == DeploymentStatus.FAILED).count();
        double successRate = totalDeployments == 0 ? 0 : ((double) successfulDeployments / totalDeployments) * 100;
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalDeployments", totalDeployments); stats.put("successfulDeployments", successfulDeployments); stats.put("failedDeployments", failedDeployments); stats.put("successRatePercentage", String.format("%.2f", successRate));
        return ResponseEntity.ok(stats);
    }
}
