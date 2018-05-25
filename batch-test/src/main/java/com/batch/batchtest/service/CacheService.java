package com.batch.batchtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.batch.batchtest.domain.Order;

@Service
public class CacheService {

	@Autowired
	OrderService service;
	
	@Cacheable(value="orders",key="#root.method")
	public List<Order> allorder() {
		return service.getAllOrders();
	}
	
}
