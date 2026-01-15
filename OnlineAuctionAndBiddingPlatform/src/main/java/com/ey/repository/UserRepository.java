package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

}
