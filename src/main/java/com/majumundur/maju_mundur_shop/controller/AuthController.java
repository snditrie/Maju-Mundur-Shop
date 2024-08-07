package com.majumundur.maju_mundur_shop.controller;

import com.majumundur.maju_mundur_shop.constant.RoleName;
import com.majumundur.maju_mundur_shop.entity.Role;
import com.majumundur.maju_mundur_shop.entity.UserAccount;
import com.majumundur.maju_mundur_shop.repository.RoleRepository;
import com.majumundur.maju_mundur_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register/merchant")
    public ResponseEntity<?> registerMerchant(@RequestBody UserAccount user) {
        Role merchantRole = roleRepository.findByRole(RoleName.ROLE_MERCHANT);
        user.setRole(Collections.singletonList(merchantRole));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setIsEnable(true);
        userRepository.save(user);
        return ResponseEntity.ok("Merchant registered successfully");
    }

    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody UserAccount user) {
        Role customerRole = roleRepository.findByRole(RoleName.ROLE_CUSTOMER);
        user.setRole(Collections.singletonList(customerRole));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setIsEnable(true);
        userRepository.save(user);
        return ResponseEntity.ok("Customer registered successfully");
    }
}
