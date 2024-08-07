package com.majumundur.maju_mundur_shop.repository;

import com.majumundur.maju_mundur_shop.constant.RoleName;
import com.majumundur.maju_mundur_shop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleName role);
}
