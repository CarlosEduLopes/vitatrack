package com.vitalis.vitatrack.modules.auth.controller;

import com.vitalis.vitatrack.modules.user.domain.User;
import com.vitalis.vitatrack.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(user.getSenha(), existingUser.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return "LOGIN OK";
    }
}