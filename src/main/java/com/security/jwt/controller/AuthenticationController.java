package com.security.jwt.controller;

import com.security.jwt.model.UserDetail;
import com.security.jwt.model.request.LoginRequest;
import com.security.jwt.model.response.LoginResponse;
import com.security.jwt.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luongnk@viettel.com.vn
 */

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = jwtUtils.generateToken(((UserDetail) authentication.getPrincipal()).getUser());
        return new LoginResponse(jwt);
    }

    @GetMapping("/me")
    public String whoami() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
