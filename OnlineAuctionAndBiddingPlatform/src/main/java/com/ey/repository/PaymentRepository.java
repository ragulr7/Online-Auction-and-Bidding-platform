package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Long> {

    Optional<Payment> findByAuctionId(Long auctionId);

}
