package com.nebulaops.dto;
import java.io.Serializable;
import java.time.LocalDateTime;
public class ProjectResponse implements Serializable {
    private Long id; private String name; private String description; private String githubRepository; private String cloudProvider; private String environment; private String ownerUsername; private LocalDateTime createdAt;
    public ProjectResponse() {}
    public ProjectResponse(Long id, String name, String description, String githubRepository, String cloudProvider, String environment, String ownerUsername, LocalDateTime createdAt) {
        this.id = id; this.name = name; this.description = description; this.githubRepository = githubRepository; this.cloudProvider = cloudProvider; this.environment = environment; this.ownerUsername = ownerUsername; this.createdAt = createdAt;
    }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public String getGithubRepository() { return githubRepository; } public void setGithubRepository(String githubRepository) { this.githubRepository = githubRepository; }
    public String getCloudProvider() { return cloudProvider; } public void setCloudProvider(String cloudProvider) { this.cloudProvider = cloudProvider; }
    public String getEnvironment() { return environment; } public void setEnvironment(String environment) { this.environment = environment; }
    public String getOwnerUsername() { return ownerUsername; } public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
