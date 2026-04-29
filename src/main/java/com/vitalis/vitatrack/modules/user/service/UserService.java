package com.vitalis.vitatrack.modules.user.service;

import com.vitalis.vitatrack.modules.user.domain.User;
import com.vitalis.vitatrack.modules.user.dto.CreateUserRequest;
import com.vitalis.vitatrack.modules.user.dto.UpdateUserRequest;
import com.vitalis.vitatrack.modules.user.dto.UserResponse;
import com.vitalis.vitatrack.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserHealthService healthService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse create(CreateUserRequest request) {
        User user = User.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .idade(request.idade())
                .gender(request.gender())
                .peso(request.peso())
                .altura(request.altura())
                .objetivo(request.objetivo())
                .nivelAtividade(request.nivelAtividade())
                .build();

        return toResponse(repository.save(user));
    }

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return toResponse(user);
    }

    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setIdade(request.idade());
        user.setGender(request.gender());
        user.setPeso(request.peso());
        user.setAltura(request.altura());
        user.setObjetivo(request.objetivo());
        user.setNivelAtividade(request.nivelAtividade());

        return toResponse(repository.save(user));
    }

    public void delete(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(user);
    }

    private UserResponse toResponse(User user) {
        double imc = healthService.calcularImc(user.getPeso(), user.getAltura());

        var classificacao = healthService.classificarImc(imc);

        double tmb = healthService.calcularTmb(
                user.getPeso(),
                user.getAltura(),
                user.getIdade(),
                user.getGender()
        );

        double get = healthService.calcularGet(
                tmb,
                user.getNivelAtividade()
        );

        var macros = healthService.calcularMacros(
                user.getPeso(),
                get,
                user.getObjetivo()
        );

        return new UserResponse(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getIdade(),
                user.getGender(),
                user.getPeso(),
                user.getAltura(),
                user.getObjetivo(),
                user.getNivelAtividade(),
                imc,
                classificacao,
                tmb,
                get,
                macros.carboidratos(),
                macros.proteinas(),
                macros.gorduras()
        );
    }
}