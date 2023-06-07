package com.tw.ticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tw.ticket.controller.TicketController.RadAndHotResponse;
import com.tw.ticket.controller.TicketController.SearchRequest;
import com.tw.ticket.controller.TicketController.SearchResponse;
import com.tw.ticket.controller.TicketDetailController.DetailResponse;
import com.tw.ticket.model.Ticket;
import com.tw.ticket.model.dao.TicketRepository;
import com.tw.ticket.model.dao.TicketSnRepository;
import com.tw.ticket.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository repository;

	@Autowired
	private TicketSnRepository snRepository;

	// 取得票券明細
	@Override
	public DetailResponse getItem(final int id) {
		final Ticket ticket = repository.findById(id).orElse(null);

		if (ticket == null) {
			return null;
		}
		final DetailResponse detailResponse = new DetailResponse(ticket);

		// 可用數量
		detailResponse.setAvailable(snRepository.countUsableSn(id));
		return detailResponse;
	}

	// 隨機票券
	@Override
	public List<RadAndHotResponse> getRandomItem() {
		final List<RadAndHotResponse> result = new ArrayList<>();

		repository.findAll().forEach(ticket -> {
			if (result.size() >= 4) {
				return;
			}
			result.add(new RadAndHotResponse(ticket));
		});
		return result;
	}

	// 熱門票券
	@Override
	public List<RadAndHotResponse> getHotItem() {
		final List<RadAndHotResponse> result = new ArrayList<>();

		repository.findAllByOrderByTotalSalesDesc().forEach(ticket -> {
			if (result.size() >= 8) {
				return;
			}
			result.add(new RadAndHotResponse(ticket));

		});
		return result;
	}

	// 搜尋票券
	@Override
	public SearchResponse getSearchItem(final SearchRequest request) {
		final Pageable pageable = PageRequest.of(	//
				request.getPage(),		// 查詢的頁數，從0起算
				request.getSize()		// 查詢的每頁筆數
		);

		final Page<Ticket> page = repository.searchTicketByKeyword(	//
				request.getKeyword(),	// 關鍵字
				request.getTypes(),		// 類型
				request.getCities(),	// 縣市
				pageable				// 分頁物件
		);

		// 轉成自己定義的物件
		final SearchResponse response = new SearchResponse();
		response.setCurPage(request.getPage());
		response.setTotalPage(page.getTotalPages());

		page.getContent().forEach(ticket -> {
			response.getTickets().add(new RadAndHotResponse(ticket));
		});

		return response;
	}

	// AI 行程，地點搜尋票券
	@Override
	public List<RadAndHotResponse> getTicket(final String destination) {
		final List<RadAndHotResponse> result = new ArrayList<>();

		repository.findByCityContaining(destination).forEach(ticket -> {
			result.add(new RadAndHotResponse(ticket));
		});
		return result;
	}

}
