package com.presente.confeitaria.controllers;


import com.presente.confeitaria.dtos.user.LoginDTO;
import com.presente.confeitaria.jwts.JwtToken;
import com.presente.confeitaria.jwts.JwtUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/enter")
@CrossOrigin("http://localhost:5173")
public class AuthenticationController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(JwtUserDetailsService detailsService, AuthenticationManager authenticationManager) {
        this.detailsService = detailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody  LoginDTO dto, HttpServletRequest request) {
        System.out.println("Processo de autenticação pelo login" + dto.getUsername());
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

            authenticationManager.authenticate(authenticationToken);

            JwtToken token = detailsService.getTokenAuthenticated(dto.getUsername());
            System.out.println("lOGIN EFETUADO POR " + dto.getUsername()) ;
            return ResponseEntity.ok(token);
        } catch (AuthenticationException ex) {
            System.out.println("Bad Credentials from username" + dto.getUsername());
        }
        return ResponseEntity
                .badRequest()
                .body("Credenciais inválidas");
    }
}