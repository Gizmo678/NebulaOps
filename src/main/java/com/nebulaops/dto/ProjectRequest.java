package com.nebulaops.dto;
import jakarta.validation.constraints.NotBlank;
public class ProjectRequest {
    @NotBlank(message = "Name is required") private String name;
    private String description;
    @NotBlank(message = "GitHub repository is required") private String githubRepository;
    private String cloudProvider;
    private String environment;
    public ProjectRequest() {}
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public String getGithubRepository() { return githubRepository; } public void setGithubRepository(String githubRepository) { this.githubRepository = githubRepository; }
    public String getCloudProvider() { return cloudProvider; } public void setCloudProvider(String cloudProvider) { this.cloudProvider = cloudProvider; }
    public String getEnvironment() { return environment; } public void setEnvironment(String environment) { this.environment = environment; }
}
