package com.presente.confeitaria.controllers;


import com.presente.confeitaria.dtos.user.LoginDTO;
import com.presente.confeitaria.jwts.JwtToken;
import com.presente.confeitaria.jwts.JwtUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/enter")
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager manager;

    public AuthenticationController(JwtUserDetailsService detailsService, AuthenticationManager manager) {
        this.detailsService = detailsService;
        this.manager = manager;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> LoginUser(@RequestBody LoginDTO loginDTO) {
        try {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
            manager.authenticate(authenticationToken);


            JwtToken token = detailsService.getTokenAuthenticated(loginDTO.getUsername());


            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }


}
