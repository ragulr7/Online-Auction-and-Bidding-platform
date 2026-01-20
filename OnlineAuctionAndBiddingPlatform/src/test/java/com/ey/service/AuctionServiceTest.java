package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.ey.dto.request.AuctionRequest;
import com.ey.dto.request.AuctionRescheduleRequest;
import com.ey.dto.response.AuctionResponse;
import com.ey.entity.Auction;
import com.ey.entity.Bid;
import com.ey.entity.Product;
import com.ey.enums.ApprovalStatus;
import com.ey.enums.AuctionStatus;
import com.ey.exception.AuctionNotFoundException;
import com.ey.exception.NoBidException;
import com.ey.exception.ProductNotApprovedException;
import com.ey.repository.AuctionRepository;
import com.ey.repository.BidRepository;
import com.ey.repository.ProductRepository;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AuctionServiceTest {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private BidRepository bidRepository;

  
    @Test
    void createAuction_success() {

        Product product = new Product();
        product.setApprovalStatus(ApprovalStatus.APPROVED);
        product.setExpectedPrice(100000.0);
        product = productRepository.save(product);

        AuctionRequest req = new AuctionRequest();
        req.setProductId(product.getProductId());
        req.setAuctionDate(LocalDate.now());
        req.setStartTime(LocalTime.now());
        req.setEndTime(LocalTime.now().plusMinutes(5));

        AuctionResponse response = auctionService.createAuction(req);

        assertNotNull(response);
        assertEquals(AuctionStatus.SCHEDULED, response.getStatus());
    }

    @Test
    void createAuction_fail_whenProductNotApproved() {

        Product product = new Product();
        product.setApprovalStatus(ApprovalStatus.PENDING);
        product = productRepository.save(product);

        AuctionRequest req = new AuctionRequest();
        req.setProductId(product.getProductId());
        req.setAuctionDate(LocalDate.now());
        req.setStartTime(LocalTime.now());
        req.setEndTime(LocalTime.now().plusMinutes(5));
        assertThrows(
                ProductNotApprovedException.class,
                () -> auctionService.createAuction(req)
        );
    }

    @Test
    void getAuctionById_success() {

        Auction auction = new Auction();
        auction.setStatus(AuctionStatus.SCHEDULED);
        auction = auctionRepository.save(auction);

        AuctionResponse response =
                auctionService.getAuctionById(auction.getAuctionId());

        assertEquals(AuctionStatus.SCHEDULED, response.getStatus());
    }

    @Test
    void getAuctionById_fail_whenNotFound() {

        assertThrows(
                AuctionNotFoundException.class,
                () -> auctionService.getAuctionById(999L)
        );
    }

  
    @Test
    void closeAuction_success() {

        Product product = new Product();
        product.setApprovalStatus(ApprovalStatus.APPROVED);
        product.setExpectedPrice(100000.0);
        product = productRepository.save(product);

        Auction auction = new Auction();
        auction.setProductId(product.getProductId());
        auction.setStatus(AuctionStatus.SCHEDULED);
        auction = auctionRepository.save(auction);

        Bid bid = new Bid();
        bid.setAuctionId(auction.getAuctionId());
        bid.setBuyerId(1L);
        bid.setAmount(120000.0);
        bidRepository.save(bid);

        AuctionResponse response =
                auctionService.closeAuction(auction.getAuctionId());

        assertEquals(AuctionStatus.COMPLETED, response.getStatus());
    }
    @Test
    void closeAuction_failure() {

        Product product = new Product();
        product.setApprovalStatus(ApprovalStatus.APPROVED);
        product.setExpectedPrice(100000.0);
        product = productRepository.save(product);

        Auction auction = new Auction();
        auction.setProductId(product.getProductId());
        auction.setStatus(AuctionStatus.SCHEDULED);
        auction = auctionRepository.save(auction);

        Bid bid = new Bid();
        bid.setAuctionId(auction.getAuctionId());
        bid.setBuyerId(1L);
        bid.setAmount(120000.0);
        bidRepository.save(bid);

        AuctionResponse response =
                auctionService.closeAuction(auction.getAuctionId());

        assertEquals(AuctionStatus.COMPLETED, response.getStatus());
    }
   
   
    @Test
    void rescheduleAuction_success() {

        Auction auction = new Auction();
        auction.setStatus(AuctionStatus.FAILED);
        auction = auctionRepository.save(auction);

        AuctionRescheduleRequest req = new AuctionRescheduleRequest();
        req.setAuctionDate(LocalDate.now().plusDays(1));
        req.setStartTime(LocalTime.of(10, 0));
        req.setEndTime(LocalTime.of(10, 30));

        AuctionResponse response =
                auctionService.rescheduleAuction(auction.getAuctionId(), req);

        assertEquals(AuctionStatus.SCHEDULED, response.getStatus());
    }
    @Test
    void rescheduleAuction_failure() {

        Auction auction = new Auction();
        auction.setStatus(AuctionStatus.FAILED);
        auction = auctionRepository.save(auction);

        AuctionRescheduleRequest req = new AuctionRescheduleRequest();
        req.setAuctionDate(LocalDate.now().plusDays(1));
        req.setStartTime(LocalTime.of(10, 0));
        req.setEndTime(LocalTime.of(10, 30));

        AuctionResponse response =
                auctionService.rescheduleAuction(auction.getAuctionId(), req);

        assertEquals(AuctionStatus.SCHEDULED, response.getStatus());
    }

   
    
}
