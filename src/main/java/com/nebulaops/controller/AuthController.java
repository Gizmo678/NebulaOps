package com.nebulaops.controller;
import com.nebulaops.dto.*;
import com.nebulaops.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }
    @PostMapping("/register") public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) { return ResponseEntity.ok(authService.register(request)); }
    @PostMapping("/login") public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) { return ResponseEntity.ok(authService.authenticate(request)); }
}
