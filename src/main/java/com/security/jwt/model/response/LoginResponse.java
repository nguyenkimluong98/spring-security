package com.security.jwt.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luongnk@viettel.com.vn
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private String token;
}
