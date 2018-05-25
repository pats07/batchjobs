package com.batch.batchtest.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class OrderStepChunkListener implements ChunkListener {

	@Override
	public void afterChunk(ChunkContext arg0) {
		
	}

	@Override
	public void afterChunkError(ChunkContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChunk(ChunkContext arg0) {
		// TODO Auto-generated method stub

	}
}
