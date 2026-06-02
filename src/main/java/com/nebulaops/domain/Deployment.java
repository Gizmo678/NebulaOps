package com.nebulaops.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "deployments")
public class Deployment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "triggered_by_id", nullable = false)
    private User triggeredBy;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private DeploymentStatus status;
    private String deploymentLogs;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    @PrePersist protected void onStart() { this.startedAt = LocalDateTime.now(); this.status = DeploymentStatus.PENDING; }
    public Deployment() {}
    public Deployment(Project project, User triggeredBy) { this.project = project; this.triggeredBy = triggeredBy; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public User getTriggeredBy() { return triggeredBy; }
    public void setTriggeredBy(User triggeredBy) { this.triggeredBy = triggeredBy; }
    public DeploymentStatus getStatus() { return status; }
    public void setStatus(DeploymentStatus status) { this.status = status; }
    public String getDeploymentLogs() { return deploymentLogs; }
    public void setDeploymentLogs(String deploymentLogs) { this.deploymentLogs = deploymentLogs; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}
