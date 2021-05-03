package com.security.jwt.entity;

import com.security.jwt.define.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author luongnk@viettel.com.vn
 */

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String role = UserRole.USER.name();
    private Boolean locked = false;
    private Boolean enabled = true;
}
