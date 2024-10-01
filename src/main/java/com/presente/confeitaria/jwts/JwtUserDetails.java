package com.presente.confeitaria.jwts;

import com.presente.confeitaria.entities.Users;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


public class JwtUserDetails extends User {

    private Users users;

    public JwtUserDetails(Users users) {
        super(users.getName(), users.getPassword(), AuthorityUtils.createAuthorityList(users.getRole().toString()));
        this.users = users;
    }

    public Long getId() {
        return this.users.getId();
    }

    public String getRole() {
        return this.users.getRole().toString();
    }
}
