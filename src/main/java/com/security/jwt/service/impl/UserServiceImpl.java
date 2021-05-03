package com.security.jwt.service.impl;

import com.security.jwt.entity.User;
import com.security.jwt.model.UserDetail;
import com.security.jwt.repository.UserRepository;
import com.security.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author luongnk@viettel.com.vn
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> loadedUser = userRepository.findByUsername(username);

        return loadedUser.map(UserDetail::new).orElse(null);
    }
}
