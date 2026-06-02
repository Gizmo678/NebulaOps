package com.nebulaops.dto;
import com.nebulaops.domain.DeploymentStatus;
import java.time.LocalDateTime;
public class DeploymentResponse {
    private Long id; private Long projectId; private String triggeredByUsername; private DeploymentStatus status; private LocalDateTime startedAt; private LocalDateTime completedAt;
    public DeploymentResponse() {}
    public DeploymentResponse(Long id, Long projectId, String triggeredByUsername, DeploymentStatus status, LocalDateTime startedAt, LocalDateTime completedAt) {
        this.id = id; this.projectId = projectId; this.triggeredByUsername = triggeredByUsername; this.status = status; this.startedAt = startedAt; this.completedAt = completedAt;
    }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getProjectId() { return projectId; } public void setProjectId(Long projectId) { this.projectId = projectId; }
    public String getTriggeredByUsername() { return triggeredByUsername; } public void setTriggeredByUsername(String triggeredByUsername) { this.triggeredByUsername = triggeredByUsername; }
    public DeploymentStatus getStatus() { return status; } public void setStatus(DeploymentStatus status) { this.status = status; }
    public LocalDateTime getStartedAt() { return startedAt; } public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; } public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}
