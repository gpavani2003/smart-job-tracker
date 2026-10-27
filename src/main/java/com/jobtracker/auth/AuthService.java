
package com.jobtracker.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobtracker.dto.RegisterRequest;
import com.jobtracker.entity.UserAccount;
import com.jobtracker.repository.UserAccountRepository;

@Service
public class AuthService {

    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserAccountRepository repository,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccount register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email is already registered");
        }

        UserAccount user = new UserAccount();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        return repository.save(user);
    }
    public void resetPassword(String email, String newPassword) {
        UserAccount user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }
    
}
