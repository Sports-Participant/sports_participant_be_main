package com.example.sports_participant_be_main.security.jwt;

import com.example.sports_participant_be_main.security.RoleS;
import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get("email", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<RoleS> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
        roles.forEach(System.out::println);
        return roles.stream()
                .map(RoleS::new)
                .collect(Collectors.toSet());
    }

}

