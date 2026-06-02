package com.nebulaops.dto;
import jakarta.validation.constraints.NotNull;
public class DeploymentRequest {
    @NotNull(message = "Project ID is required") private Long projectId;
    public DeploymentRequest() {}
    public Long getProjectId() { return projectId; } public void setProjectId(Long projectId) { this.projectId = projectId; }
}
