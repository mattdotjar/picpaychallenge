package com.picpaychallenge.picpay_challenge.domain.user;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity(name = "users")
@Table("users")
@EqualsAndHashCode(of = "id")
public class User {

    @@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;


}
