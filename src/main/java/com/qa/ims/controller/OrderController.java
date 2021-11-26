package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO OrderDAO;
	private Utils utils;

	public OrderController(OrderDAO OrderDAO, Utils utils) {
		super();
		this.OrderDAO = OrderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = OrderDAO.readAll();
		for (Order Orders : orders) {
			LOGGER.info(orders);
		}
		return orders;
	}

	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter the order ID");
		Long orderId = utils.getLong();
		Order orders = OrderDAO.create(new Order(orderId));
		LOGGER.info("Order created");
		return orders;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long orderId = utils.getLong();
		Order orders = OrderDAO.update(new Order(orderId));
		LOGGER.info("Order Updated");
		return orders;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long orderId = utils.getLong();
		return OrderDAO.delete(orderId);
	}

}
