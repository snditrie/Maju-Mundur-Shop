package com.majumundur.maju_mundur_shop.entity;

import com.majumundur.maju_mundur_shop.constant.ConstantTable;
import com.majumundur.maju_mundur_shop.constant.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = ConstantTable.ROLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName role;
}
