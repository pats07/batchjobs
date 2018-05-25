package com.batch.batchtest.step;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.batch.batchtest.domain.Order;

public class OrderReader implements ItemReader<Order>{

	private List<Order> list;
	private int count = 0;
	@Override
	public Order read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		if(count < list.size()) {
			return list.get(count++);
		}		
		return null;
	}

	public List<Order> getList() {
		return list;
	}

	public void setList(List<Order> list) {
		this.list = list;
	}
	
}
