package com.tw.ticket.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tw.ticket.model.Ticket;
import com.tw.ticket.model.TicketFavorite;
import com.tw.ticket.model.TicketFavorite.PrimaryKey;

@Repository
public interface TicketFavoriteRepository extends JpaRepository<TicketFavorite, PrimaryKey> {
	public List<TicketFavorite> findByKeyMemberId(int memberId);
	//刪除
	// void deleteTicketFavorite(Integer ticketId); 
	 public void deleteByKeyMemberId(int memberId);
	//public List<TicketFavorite> deleteByKeyTicketId(int ticketId);
	//public void deleteByKeyTicket(Ticket ticket);
	public void deleteByKeyTicket(PrimaryKey key);
}
