package com.security.jwt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luongnk@viettel.com.vn
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    private String username;
    private String password;
}
