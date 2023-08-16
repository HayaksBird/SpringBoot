USE assortment;

DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;

CREATE TABLE product_category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_category_id BIGINT,
    description VARCHAR(255),
    price DECIMAL(10, 2),
    FOREIGN KEY (product_category_id) REFERENCES Product_category(id)
);

DELETE FROM product_category;
ALTER TABLE product_category AUTO_INCREMENT = 1;

INSERT INTO product_category (description)
VALUES ("Fresh Produce"),
	   ("Dairy Products"),
       ("Meat and Poultry");
       
DELETE FROM product;
ALTER TABLE product AUTO_INCREMENT = 1;

INSERT INTO product (product_category_id, description, price)
VALUES (1, 'Apples', 2.99),
	   (1, 'Bananas', 1.99),
       (1, 'Carrots', 1.49),
       (2, 'Milk', 3.49),
       (2, 'Cheese', 5.99),
       (3, 'Chicken', 8.99),
       (3, 'Beef', 12.99);