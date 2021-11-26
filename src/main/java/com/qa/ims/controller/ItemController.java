package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO ItemDAO;
	private Utils utils;

	public ItemController(ItemDAO ItemDAO, Utils utils) {
		super();
		this.ItemDAO = ItemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Items> readAll() {
		List<Items> items = ItemDAO.readAll();
		for (Items item : items) {
			LOGGER.info(items);
		}
		return items;
	}

	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Items create() {
		LOGGER.info("Please enter the item name or ID");
		String itemName = utils.getString();
		Long itemId = utils.getLong();
		Items item = ItemDAO.create(new Items(itemId, itemName));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Items update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter a first name");
		String itemName = utils.getString();
		Items item = ItemDAO.update(new Items(itemId, itemName));
		LOGGER.info("Customer Updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long itemId = utils.getLong();
		return ItemDAO.delete(itemId);
	}

}
