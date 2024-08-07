package com.majumundur.maju_mundur_shop.repository;

import com.majumundur.maju_mundur_shop.entity.Product;
import com.majumundur.maju_mundur_shop.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByMerchantId(Long merchantId);
}
