package com.springbootblog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootblog.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
Optional<Role> findByName(String name);
}
