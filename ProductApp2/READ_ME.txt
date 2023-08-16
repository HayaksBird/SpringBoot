Before running the application, make sure to run the sql scripts that are located
in the 'resources' folder. Run the 'init' before the 'func'. I used the MySQL database
for this project.

I gave full/partial CRUD support for the following tables:
* product - full
* product_category - partial (cannot update)
* barcode_type - partial (cannot update & delete)
* unit - partial (cannot update)

product_category: If the product categories were to be updated,
then the barcodes of each product would have been needed to be reconsidered.
Since this is really tedious and could potentially create bugs, I've decided
to exclude this feature.

barcode_type: The barcodes are assigned to a product under a specific rule.
For this reason, the update/delete procedure could create a confusion among products'
barcodes or even leave some products without a barcode whatsoever.

unit: the same reason as for the product_category.