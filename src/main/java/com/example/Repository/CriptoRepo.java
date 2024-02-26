package com.example.Repository;

import com.example.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriptoRepo extends JpaRepository<User, String> {
    User findByNombreUsuario(String Username);
}
