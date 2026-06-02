package com.nebulaops.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "projects")
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    private String description;
    @Column(nullable = false) private String githubRepository;
    private String cloudProvider;
    private String environment;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User owner;
    private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { this.createdAt = LocalDateTime.now(); }
    public Project() {}
    public Project(String name, String description, String githubRepository, String cloudProvider, String environment, User owner) {
        this.name = name; this.description = description; this.githubRepository = githubRepository; this.cloudProvider = cloudProvider; this.environment = environment; this.owner = owner;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getGithubRepository() { return githubRepository; }
    public void setGithubRepository(String githubRepository) { this.githubRepository = githubRepository; }
    public String getCloudProvider() { return cloudProvider; }
    public void setCloudProvider(String cloudProvider) { this.cloudProvider = cloudProvider; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
