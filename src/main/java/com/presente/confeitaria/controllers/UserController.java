package com.presente.confeitaria.controllers;

import com.presente.confeitaria.dtos.user.UserRequestDTO;
import com.presente.confeitaria.services.UsersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UsersService service;

    public UserController(UsersService service) {
        this.service = service;
    }
    @PostMapping
    public void RegisterUser(@RequestBody UserRequestDTO requestDTO) throws Exception {
        if (requestDTO.getName().isEmpty() || requestDTO.getEmail().isEmpty() || requestDTO.getTelephone().isEmpty() || requestDTO.getPassword().isEmpty()) {
            throw new Exception("Usuário não informou todos os dados pedidos");
        } else {
            service.saveUser(requestDTO);
        }
    }


}

