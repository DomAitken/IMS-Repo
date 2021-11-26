package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;

public interface Dao<T> {

	List<T> readAll();
	
	T read(Long id);

	T create(T t);

	T update(T t);

	int delete(long id);

	T modelFromResultSet(ResultSet resultSet) throws SQLException;

	/**
	 * Updates an item in the database
	 * 
	 * @param customer - takes in an item object, the id field will be used to
	 *                 update that item in the database
	 * @return
	 */
	Order update(Order order, Customer customerId);

	Order readLatest(Long orderId);
}
