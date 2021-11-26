package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemId = resultSet.getLong("itemId");
		String itemName = resultSet.getString("itemName");
		
		return new Items(itemId, itemName);
	}

	/**
	 * Reads all items from the database
	 * 
	 * @return A list of items
	 */
	@Override
	public List<Items> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<Items> Items = new ArrayList<>();
			while (resultSet.next()) {
				Items.add(modelFromResultSet(resultSet));
			}
			return Items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Items readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY itemId DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Items create(Items items) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO items(itemName) VALUES (?)");) {
			statement.setString(1, items.getItemName());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Items read(Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE itemId = ?");) {
			statement.setLong(1, itemId);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an item in the database
	 * 
	 * @param customer - takes in an item object, the id field will be used to
	 *                 update that item in the database
	 * @return
	 */
	@Override
	public Items update(Items items) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE items SET itemName = ? WHERE itemId = ?");) {
			statement.setString(1, items.getItemName());
			statement.setLong(2, items.getItemId());
			statement.executeUpdate();
			return read(items.getItemId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an Item in the database
	 * 
	 * @param id - id of the item
	 */
	@Override
	public int delete(long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE itemId = ?");) {
			statement.setLong(1, itemId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Order update(Order order, Customer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order readLatest(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
