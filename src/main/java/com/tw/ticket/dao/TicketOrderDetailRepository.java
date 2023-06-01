package com.tw.ticket.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tw.ticket.model.TicketOrderDetail;

@Repository
public interface TicketOrderDetailRepository extends JpaRepository<TicketOrderDetail, Integer> {
	//
}
