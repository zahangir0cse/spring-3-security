package com.crackcode.zahangir.service.impl;

import com.crackcode.zahangir.entity.User;
import com.crackcode.zahangir.repository.UserRepository;
import com.crackcode.zahangir.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
