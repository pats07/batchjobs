package com.batch.batchtest.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.batch.batchtest.domain.Order;
import com.batch.batchtest.domain.Vendor;
import com.batch.batchtest.util.SqlConstants;

@Repository("orderRepo")
public class OrderRepository {

	private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Order> getAllOrder() {
		return jdbcTemplate.query(SqlConstants.GET_ALL_ORDERS, new BeanPropertyRowMapper<Order>(Order.class));
	}

	public Vendor getVendorById(BigDecimal id) {

		try {
			return jdbcTemplate.queryForObject(SqlConstants.GET_VENDOR_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<Vendor>(Vendor.class));
		} catch (Exception e) {
			logger.error(e.toString());
		}

		return null;
	}

	public Long isOrderExistinStaging(BigDecimal orderid) {
		try {
			return (Long)jdbcTemplate.queryForObject(SqlConstants.IS_RECORD_EXIST,new Object[]{orderid}, Long.class);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}

	public Long insertUpdateStaging(String sql, Object[] args) {
		return (long) jdbcTemplate.update(sql, args);
	}

}
