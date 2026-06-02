package com.nebulaops.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendDeploymentSuccessAlert(String projectName, String environment) {
        log.info("NOTIFICATION: Deployment for project '{}' to '{}' was SUCCESSFUL.", projectName, environment);
    }
    public void sendDeploymentFailureAlert(String projectName, String environment, String errorDetails) {
        log.error("NOTIFICATION: Deployment for project '{}' to '{}' FAILED. Details: {}", projectName, environment, errorDetails);
    }
}
