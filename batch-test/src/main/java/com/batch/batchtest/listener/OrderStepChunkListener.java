package com.batch.batchtest.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class OrderStepChunkListener implements ChunkListener {

	private static final Logger logger = LoggerFactory.getLogger(OrderStepChunkListener.class);
	private static int passCount = 1;
	
	@Override
	public void afterChunk(ChunkContext arg0) {
		logger.info("Chunk Pass Completed");
	}

	@Override
	public void afterChunkError(ChunkContext arg0) {
		
	}

	@Override
	public void beforeChunk(ChunkContext arg0) {
		logger.info("Chunk Pass "+passCount+" started");
		passCount += 1;
	}
}
