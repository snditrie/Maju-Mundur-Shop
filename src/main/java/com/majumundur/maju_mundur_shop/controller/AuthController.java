package com.majumundur.maju_mundur_shop.controller;

import com.majumundur.maju_mundur_shop.constant.RoleName;
import com.majumundur.maju_mundur_shop.entity.UserAccount;
import com.majumundur.maju_mundur_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register/merchant")
    public ResponseEntity<?> registerMerchant(@RequestBody UserAccount user) {
        userService.registerUser(user, RoleName.ROLE_MERCHANT);
        return ResponseEntity.ok("Merchant registered successfully");
    }

    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody UserAccount user) {
        userService.registerUser(user, RoleName.ROLE_CUSTOMER);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("Logged in as: " + authentication.getName());
    }
}
