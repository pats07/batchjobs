package com.batch.batchtest.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.batch.batchtest.domain.Order;
import com.batch.batchtest.service.CacheService;
import com.batch.batchtest.service.OrderService;
import com.batch.batchtest.step.OrderReader;

@Component
public class OrderStepExecutionListener implements StepExecutionListener {

	private static final Logger LOG = LoggerFactory.getLogger(OrderStepExecutionListener.class);

	@Autowired
	OrderService service;
	
	@Autowired
	OrderReader reader;
	
	@Autowired
	CacheService cService;
	
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		LOG.info("Step 1 Execution completed");		
		return ExitStatus.COMPLETED;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		LOG.info("Caching started..");
		cService.allorder();
		LOG.info("Caching done..");
		LOG.info("Step 1 Execution started..");
		List<Order> list = service.getAllOrders();
		LOG.info("Read count : " + list.size());
		reader.setList(list);
	}

}
