package com.presente.confeitaria.services;

import com.presente.confeitaria.dtos.UserRequestDTO;
import com.presente.confeitaria.entities.Users;
import com.presente.confeitaria.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository repository;

  public UsersService (BCryptPasswordEncoder passwordEncoder, UserRepository  repository ) {
      this.passwordEncoder = passwordEncoder;
      this.repository = repository;
  }

    public void saveUser(UserRequestDTO requestDTO) {
        Users users = new Users();
        String cripted = passwordEncoder.encode(requestDTO.getPassword());
        users.setName(requestDTO.getName());
        users.setEmail(requestDTO.getEmail());
        users.setPassword(cripted);
        users.setTelephone(requestDTO.getTelephone());
        repository.save(users);
    }

    public Users searchByName(String username) {
        return repository.findByName(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário não encontrado para o nome: " + username)));
    }
}
