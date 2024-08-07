package com.majumundur.maju_mundur_shop.repository;

import com.majumundur.maju_mundur_shop.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
