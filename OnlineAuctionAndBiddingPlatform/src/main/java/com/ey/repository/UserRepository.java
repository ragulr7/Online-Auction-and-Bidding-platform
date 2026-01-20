package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.User;
import com.ey.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByRole(Role role);



}
