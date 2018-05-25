package com.batch.batchtest.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.batchtest.domain.OrderStaging;
import com.batch.batchtest.service.OrderService;
import com.batch.batchtest.util.SqlConstants;

public class OrderWriter implements ItemWriter<OrderStaging>{

	@Autowired
	OrderService service;
	
	@Override
	public void write(List<? extends OrderStaging> orderStagings) throws Exception {
		
		for(OrderStaging st : orderStagings) {
			
			long count = service.isOrderExistinStaging(st.getOrderid());
			
			if(count > 0) {
				Object[] args = new Object[]{
						st.getVendorid(),st.getLAST_NAME(),st.getEMAIL(),
						st.getGENDER(),st.getIP_ADDRESS(),st.getPHONE(),st.getCOMPANY(),
						st.getCOUNTRY(),st.getOrderid()
				};
				
				service.insertUpdateStaging(SqlConstants.UPDATE_STAGING, args);
				
			} else {
				     
				Object[] args = new Object[]{
						st.getOrderid(), st.getVendorid(),st.getLAST_NAME(),st.getEMAIL(),
						st.getGENDER(),st.getIP_ADDRESS(),st.getPHONE(),st.getCOMPANY(),
						st.getCOUNTRY()
				};
				
				long insCnt = service.insertUpdateStaging(SqlConstants.INSERT_STAGING,args);
			}
			
		}
		
	}

	
}
