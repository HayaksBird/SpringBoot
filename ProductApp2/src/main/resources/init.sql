USE assortment;

DROP TABLE IF EXISTS barcode;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS barcode_type;
DROP TABLE IF EXISTS config;



-- Initialize the tables.
CREATE TABLE unit (
	unit VARCHAR(8) PRIMARY KEY
);

CREATE TABLE product_category (
	id INT PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE product (
	code INT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
    category_id INT NOT NULL,
    brand VARCHAR(50) NOT NULL,
    unit VARCHAR(20) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES product_category(id),
    FOREIGN KEY (unit) REFERENCES unit(unit)
);

CREATE TABLE barcode_type (
	type VARCHAR(50) PRIMARY KEY
);

CREATE TABLE barcode (
	code INT PRIMARY KEY,
    product_code INT,
	type VARCHAR(50),
    FOREIGN KEY (type) REFERENCES barcode_type(type),
    FOREIGN KEY (product_code) REFERENCES product(code)
);

CREATE TABLE config (
	category_id INT,
    product_code INT,
    product_barcode INT,
    scale_barcode INT,
    plu_barcode INT
);



-- Initialize predefined values.
INSERT INTO barcode_type (type)
VALUES	('product barcode'),
		('scale barcode'),
        ('PLU barcode');	-- price look-up barcode

INSERT INTO unit (unit) 
VALUES 	('kilogram'),
		('piece');
        
INSERT INTO config (category_id, product_code, product_barcode, scale_barcode, plu_barcode)
VALUES	(10, 100, 100000000, 100, 1000);