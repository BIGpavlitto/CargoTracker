package com.example.cargologger.models;

import com.example.cargologger.models.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class LoginAuthentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull
    @Size(min=6, message="ID must be 6 characters long!")
    private String loginId;
    @Column(nullable = false)
    @NotNull
    private String password;
    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
