package com.solveast.rreps.config;

import com.google.common.hash.Hashing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

/**
 * Created by Андрей on 22.01.2017.
 */
public class ProjectPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return Hashing.sha256()
                .hashString(rawPassword, StandardCharsets.UTF_8)
                .toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        String encode = encode(rawPassword);
        if (encode.equals(encodedPassword))
            return true;
        return false;
    }
}
