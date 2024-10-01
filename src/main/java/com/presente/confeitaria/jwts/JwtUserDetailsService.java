package com.presente.confeitaria.jwts;

import com.presente.confeitaria.entities.Users;
import com.presente.confeitaria.services.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    //ffinalizar esta class
    private final UsersService usersService;

    public JwtUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersService.searchByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        // Retorna uma instância de UserDetails personalizada
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    @Deprecated
    public JwtToken getTokenAuthenticated(String username) {
        Users user = usersService.searchByName(username); // Busca o usuário pelo nome de usuário
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Users.Role role = user.getRole(); // Obtém a função do usuário
        Long userId = user.getId(); // Obtém o ID do usuário
        String email = user.getEmail(); // Obtém o email do usuário

        return JwtUtils.createToken(username, userId, role.name().substring("ROLE_".length()));
    }

}

