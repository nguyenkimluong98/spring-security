package com.security.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luongnk@viettel.com.vn
 */

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @GetMapping("/me")
    private String whoami() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
