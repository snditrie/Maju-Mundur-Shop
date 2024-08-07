package com.majumundur.maju_mundur_shop.service;

import com.majumundur.maju_mundur_shop.constant.RoleName;
import com.majumundur.maju_mundur_shop.entity.Role;
import com.majumundur.maju_mundur_shop.entity.UserAccount;
import com.majumundur.maju_mundur_shop.repository.RoleRepository;
import com.majumundur.maju_mundur_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(UserAccount user, RoleName roleName) {
        Role role = roleRepository.findByRole(roleName);
        user.setRole(Collections.singletonList(role));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setIsEnable(true);
        userRepository.save(user);
    }

    public UserAccount authenticateUser(String username, String password) throws AuthenticationException {
        UserAccount user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
