package com.souvik.fresh_votes.security;

import com.souvik.fresh_votes.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Set;

public class CustomSecurityUser extends User implements UserDetails {

    @Serial
    private static final long serialVersionUID = -7764979013243899001L;

    public CustomSecurityUser() {}

    public CustomSecurityUser(User user) {
        super.setAuthorities(user.getAuthorities());
        super.setName(user.getName());
        super.setPassword(user.getPassword());
        super.setUsername(user.getUsername());
        super.setId(user.getId());
    }

    @Override
    public Set<Authority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
