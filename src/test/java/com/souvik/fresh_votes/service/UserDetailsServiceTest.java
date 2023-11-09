package com.souvik.fresh_votes.service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceTest {

    @Test
    public void generate_encrypted_password() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        final String rawPassword = "root";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);

        Assert.assertThat(rawPassword, CoreMatchers.not(encodedPassword));
    }
}
