package com.security.jwt.repository;

import com.security.jwt.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author luongnk@viettel.com.vn
 */

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(final String username);
}
