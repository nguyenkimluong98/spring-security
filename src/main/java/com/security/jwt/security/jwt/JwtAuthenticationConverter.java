package com.security.jwt.security.jwt;

import com.security.jwt.entity.User;
import com.security.jwt.model.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @author luongnk@viettel.com.vn
 */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationConverter implements AuthenticationConverter {

    public static final String AUTHENTICATION_SCHEME_BEARER = "Bearer";

    private final JwtUtils jwtUtils;

    @Override
    public Authentication convert(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            return null;
        }

        header = header.trim();

        if (!StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BEARER)) {
            return null;
        }

        if (header.equalsIgnoreCase(AUTHENTICATION_SCHEME_BEARER) || header.length() < (AUTHENTICATION_SCHEME_BEARER.length() + 1)) {
            throw new BadCredentialsException("Empty bearer authentication token");
        }

        String token = header.substring(AUTHENTICATION_SCHEME_BEARER.length() + 1);

        User user = jwtUtils.parseToken(token);

        if (user == null) {
            throw new BadCredentialsException("Invalid authentication token");
        }

        JwtAuthenticationToken result = new JwtAuthenticationToken(new UserDetail(user),
                token, Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

        result.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return result;
    }
}
