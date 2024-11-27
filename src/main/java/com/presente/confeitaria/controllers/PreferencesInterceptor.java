package com.presente.confeitaria.controllers;

import com.presente.confeitaria.jwts.JwtUtils;
import com.presente.confeitaria.services.PreferencesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class PreferencesInterceptor implements HandlerInterceptor {

    private final PreferencesService service;

    public PreferencesInterceptor(PreferencesService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);

        if (token != null && JwtUtils.isTokenValid(token)) {
            // Pegar informações do token
            Long userId = JwtUtils.getUserIdFromToken(token);
            String httpMethod = request.getMethod();
            String endPoint = request.getRequestURI();

            // Logando as interações para verificação
            System.out.println("Capturando interação - Usuario ID: " + userId + ", Método: " + httpMethod + ", Endpoint: " + endPoint);

            // Salvando no banco
            service.saveInteraction(userId, endPoint, httpMethod);
        }
        return true;  // Continua a requisição
    }
}
