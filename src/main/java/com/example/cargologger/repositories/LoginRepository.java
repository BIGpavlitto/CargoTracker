package com.example.cargologger.repositories;

import com.example.cargologger.models.LoginAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<LoginAuthentication, Long> {
    Optional<LoginAuthentication> findByLoginId(String id);
    Optional<LoginAuthentication> findByUserId(Long id);
    Optional<Boolean> existsByLoginId(String input);
}
