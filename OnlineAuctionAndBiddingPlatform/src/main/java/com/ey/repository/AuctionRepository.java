package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Auction;
import com.ey.enums.AuctionStatus;

@Repository
public interface AuctionRepository extends JpaRepository<Auction , Long> {
	List<Auction> findByStatus(AuctionStatus status);
	List<Auction> findByStatusIn(List<AuctionStatus> statuses);
	


}
