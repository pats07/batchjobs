package com.batch.batchtest.util;

public class SqlConstants {

	public static final String GET_ALL_ORDERS = "SELECT * FROM ORDER_TRANSACTIONS";
	public static final String GET_VENDOR_BY_ID = "SELECT * FROM VENDOR WHERE VENDORID = ?";
	public static final String IS_RECORD_EXIST = "SELECT COUNT(*) FROM ORDERSTAGING WHERE ORDERID = ?";
	public static final String INSERT_STAGING = "INSERT INTO ORDERSTAGING ( " 
												+ " ORDERID, VENDORID, LAST_NAME, EMAIL,"
												+ " GENDER,IP_ADDRESS,PHONE,COMPANY,COUNTRY" 
												+ " ) VALUES (?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_STAGING = "UPDATE ORDERSTAGING SET VENDORID = ?, LAST_NAME = ?, EMAIL = ?,"
												+ " GENDER = ?,IP_ADDRESS = ?,PHONE = ?,COMPANY = ?,COUNTRY = ?"
												+ " WHERE ORDERID = ?";

}
