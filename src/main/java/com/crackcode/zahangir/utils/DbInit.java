package com.crackcode.zahangir.utils;

import com.crackcode.zahangir.entity.Role;
import com.crackcode.zahangir.entity.User;
import com.crackcode.zahangir.repository.UserRepository;
import com.crackcode.zahangir.service.UtilityService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Collections;

@Component
public class DbInit {

    @Value("${system.user.username}")
    private String username;

    @Value("${system.user.password}")
    private String password;
    private final UserRepository userRepository;
    private final UtilityService utilityService;
    private final PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, UtilityService utilityService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.utilityService = utilityService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData(){
        int userExistCount = userRepository.countByUsername(username);
        if(userExistCount == 0){
            Role role = utilityService.getRole(RoleConstraint.SYSTEM_ADMIN_ROLE);
            User user = new User();
            user.setName("MD. MASUM RANA");
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoles(Collections.singleton(role));
            userRepository.save(user);
        }
    }
}
