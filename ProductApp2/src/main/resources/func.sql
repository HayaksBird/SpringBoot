USE assortment;

-- Procedure which initializes the barcodes for the newly inserted products.
DROP PROCEDURE IF EXISTS create_barcodes;
DELIMITER //

CREATE PROCEDURE create_barcodes(product_code INT, unit VARCHAR(8), category VARCHAR(255)) 
BEGIN
	DECLARE pr_bar INT;
    DECLARE sc_bar INT;
    DECLARE plu_bar INT;
    
    -- Generate the product barcode
    SELECT product_barcode INTO pr_bar FROM config;
    UPDATE config SET product_barcode = pr_bar + 1;
    
    -- Generate the scale barcode
	SELECT scale_barcode INTO sc_bar FROM config;
    UPDATE config SET scale_barcode = sc_bar + 1;
    SET sc_bar = CONCAT(product_code, sc_bar);
    
    -- Generate the PLU barcode
    SELECT plu_barcode INTO plu_bar FROM config;
    UPDATE config SET plu_barcode = plu_bar + 1;
    
	IF unit = 'kilogram' AND category = 'fruit' THEN
		INSERT INTO barcode (code, product_code, type)
        VALUES 	(pr_bar, product_code, 'product barcode'),
				(plu_bar, product_code, 'PLU barcode');
	ELSEIF unit = 'kilogram' AND category = 'fish' THEN
		INSERT INTO barcode (code, product_code, type)
        VALUES 	(pr_bar, product_code, 'product barcode'),
				(sc_bar, product_code, 'scale barcode');
	ELSEIF unit = 'piece' AND category = 'fish' THEN
		INSERT INTO barcode (code, product_code, type)
        VALUES 	(plu_bar, product_code, 'PLU barcode');
	ELSEIF category = 'meat' THEN
		INSERT INTO barcode (code, product_code, type)
        VALUES 	(sc_bar, product_code, 'scale barcode');
	ELSE 
		INSERT INTO barcode (code, product_code, type)
        VALUES 	(pr_bar, product_code, 'product barcode');
	END IF;
END;
//

DELIMITER ;



/*
The triggers below are conserned with 2 main things:
	1) Initialize the product's and the product_category's tables' primary keys.
    2) Manage the creation/update processes of the barcodes.
*/
DELIMITER //

-- For product_category generate a 2 digit id.
CREATE TRIGGER manage_product_category
BEFORE INSERT ON product_category
FOR EACH ROW
BEGIN
	DECLARE val INT;
    
    SELECT category_id INTO val FROM config;
	SET NEW.id = val;
    UPDATE config SET category_id = val + 1;
END;
//

-- Specify the concatenation operation to be performed on a product code right after the insert.
CREATE TRIGGER manage_product
BEFORE INSERT ON product
FOR EACH ROW
BEGIN
	DECLARE val INT;
	
    SELECT product_code INTO val FROM config;
    SET NEW.code = CONCAT(NEW.category_id, val);
    UPDATE config SET product_code = val + 1;
END;
//

-- Add the barcodes for the newly inserted product.
CREATE TRIGGER add_barcodes
AFTER INSERT ON product
FOR EACH ROW
BEGIN
	DECLARE des VARCHAR(255);
    
    SELECT description INTO des FROM product_category WHERE id = NEW.category_id;    
	CALL create_barcodes(NEW.code, NEW.unit, des);
END;
//

-- Update the barcodes for the update product.
CREATE TRIGGER update_barcodes
AFTER UPDATE ON product
FOR EACH ROW
BEGIN
	DECLARE des VARCHAR(255);
    
     IF NEW.category_id != OLD.category_id THEN
		DELETE FROM barcode WHERE product_code = NEW.code;
		SELECT description INTO des FROM product_category WHERE id = NEW.category_id;
		CALL create_barcodes(NEW.code, NEW.unit, des);
	 END IF;
END;
//

DELIMITER ;