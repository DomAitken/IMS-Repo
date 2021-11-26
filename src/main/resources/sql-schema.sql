CREATE SCHEMA IF NOT EXISTS `imsproject`;

USE `imsproject` ;

CREATE TABLE IF NOT EXISTS `imsproject`.`customer` (
    `customerId` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customerId`)
);

CREATE TABLE IF NOT EXISTS 'imsproject'.'items' (
  itemId INT(11) NOT NULL AUTO_INCREMENT,
  itemName VARCHAR(80) NOT NULL,
  PRIMARY KEY (itemId)
);

CREATE TABLE IF NOT EXISTS 'imsproject'.'order_items' (
	orderId INT(11) NOT NULL AUTO_INCREMENT,
    itemId INT(11) NOT NULL,
    customerId INT(11) NOT NULL,
    PRIMARY KEY(orderId),
    FOREIGN KEY(itemId) REFERENCES items(itemId),
    FOREIGN KEY(customerId) REFERENCES customers(customer_id)
);