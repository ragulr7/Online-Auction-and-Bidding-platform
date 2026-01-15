package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction , Long> {

}
