package com.batch.batchtest.step;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.batchtest.domain.Order;
import com.batch.batchtest.domain.OrderStaging;
import com.batch.batchtest.service.OrderService;


public class OrderProcessor implements ItemProcessor<Order, OrderStaging>{

	@Autowired
	OrderService service;
	
	@Override
	public OrderStaging process(Order order) throws Exception {
		return service.processOrder(order);
	}

}
