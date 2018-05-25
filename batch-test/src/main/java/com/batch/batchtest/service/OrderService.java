package com.batch.batchtest.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batch.batchtest.domain.Order;
import com.batch.batchtest.domain.OrderStaging;
import com.batch.batchtest.domain.Vendor;
import com.batch.batchtest.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public List<Order> getAllOrders() {
		return orderRepository.getAllOrder();
	}
	
	public OrderStaging processOrder(Order order) {
		
		Vendor vendor = orderRepository.getVendorById(order.getVendorid());
		OrderStaging staging = null;
		if(vendor != null) {
			staging = new OrderStaging();
			staging.setCOMPANY(order.getCOMPANY());
			staging.setCOUNTRY(order.getCOUNTRY());
			staging.setEMAIL(order.getEMAIL());
			staging.setGENDER(order.getGENDER());
			staging.setIP_ADDRESS(order.getIP_ADDRESS());
			staging.setLAST_NAME(order.getLAST_NAME());
			staging.setOrderid(order.getOrderid());
			staging.setVendorid(vendor.getVENDORID());
			staging.setPHONE(order.getPHONE());
		}
		
		return staging;		
	}

	public Long isOrderExistinStaging(BigDecimal orderid) {
		return orderRepository.isOrderExistinStaging(orderid);
	}

	public Long insertUpdateStaging(String sql, Object[] args) {
		return orderRepository.insertUpdateStaging(sql,args);		
	}
	
	
}
