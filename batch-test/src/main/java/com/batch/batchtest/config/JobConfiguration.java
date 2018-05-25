package com.batch.batchtest.config;

import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.batchtest.domain.Order;
import com.batch.batchtest.domain.OrderStaging;
import com.batch.batchtest.listener.OrderStepChunkListener;
import com.batch.batchtest.listener.OrderStepExecutionListener;
import com.batch.batchtest.step.OrderProcessor;
import com.batch.batchtest.step.OrderReader;
import com.batch.batchtest.step.OrderWriter;



@Configuration
public class JobConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(JobConfiguration.class);

	@Autowired
	Environment env;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public OrderStepExecutionListener orderStepExecutionListener;

	@Autowired
	public OrderStepChunkListener orderStepchunkListener;


	@Bean
	public JobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository());
		return jobLauncher;
	}

	@Bean
	public JobRepository jobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDatabaseType(DatabaseType.ORACLE.name());
		factory.setIsolationLevelForCreate("READ_COMMITED");
		factory.setTransactionManager(transactionManager());
		return factory.getObject();
	}

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("connection.driver"));
		dataSource.setUrl(env.getProperty("connection.url"));
		dataSource.setUsername(env.getProperty("connection.username"));
		dataSource.setPassword(env.getProperty("connection.password"));
		if (dataSource != null) {
			LOG.debug("Datasource configured");
		}
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;

	}

	@Bean
	public Job job() {

		return jobBuilderFactory.get("OrderJob").preventRestart().incrementer(new JobParametersIncrementer() {

			@Override
			public JobParameters getNext(JobParameters parameters) {
				return new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
			}
		}).start(step1()).build();
	}

	public Step step1() {

		return stepBuilderFactory.get("step1").<Order, OrderStaging> chunk(100).reader(orderReader())
				.processor(orderProcessor()).writer(orderWriter()).listener(orderStepchunkListener)
				.listener(orderStepExecutionListener).build();
	}

	@Bean
	public OrderWriter orderWriter() {
		return new OrderWriter();
	}

	@Bean
	public OrderProcessor orderProcessor() {
		return new OrderProcessor();
	}

	@Bean
	public OrderReader orderReader() {
		return new OrderReader();
	}

	
}
