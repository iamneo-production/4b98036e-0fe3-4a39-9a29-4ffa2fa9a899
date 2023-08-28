package com.virtusa.hackathon.faultReporting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.hackathon.faultReporting.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
	Ticket findByTicketId(Long ticketId);
	@Query("SELECT t FROM Ticket t WHERE t.customer.customerId = :customerId AND t.status = :status")
    List<Ticket> findTicketsByCustomerByStatus(@Param("customerId") Long customerId,@Param("status") String status);

}
