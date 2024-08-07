package com.majumundur.maju_mundur_shop.controller;

import com.majumundur.maju_mundur_shop.entity.Product;
import com.majumundur.maju_mundur_shop.entity.UserAccount;
import com.majumundur.maju_mundur_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/merchant")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product product, @AuthenticationPrincipal UserAccount user) {
        if (!user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MERCHANT"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only merchants can create products");
        }
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, @AuthenticationPrincipal UserAccount user) {
        Product product = productService.findById(id);
        if (!product.getMerchant().equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only delete your own products");
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct, @AuthenticationPrincipal UserAccount user) {
        Product product = productService.findById(id);
        if (!product.getMerchant().equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only update your own products");
        }
        return ResponseEntity.ok(productService.updateProduct(id, updatedProduct));
    }

    @GetMapping("/products/merchant")
    public ResponseEntity<List<Product>> getMerchantProducts(@AuthenticationPrincipal UserAccount user) {
        if (!user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MERCHANT"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Menggunakan build() untuk mengembalikan ResponseEntity tanpa body
        }
        List<Product> products = productService.findProductsByMerchant(user.getId());
        return ResponseEntity.ok(products);
    }

}
