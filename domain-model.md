````dtd
1.
As a member of the public,
So I can order a bagel before work,
I'd like to add a specific type of bagel to my basket.
````

ArrayList<Product> products
int size

| Classes                    | Methods                                             | Member Variables | Scenario                                         | Output/Result                                     |
|----------------------------|-----------------------------------------------------|------------------|--------------------------------------------------|---------------------------------------------------|
| Basket, Product, Inventory | Basket::addToBasket(Product bagel)                  |                  | I want to add an onion bagel, basket is not full | Add Product to products, return true              |
|                            | Basket::checkStock(Inventory inventory, String sku) |                  |                                                  |                                                   |
|                            | Product::getName()                                  |                  |                                                  |                                                   |
|                            | Product::getPrice()                                 |                  |                                                  |                                                   |
|                            | Product::getSku()                                   |                  | I want to add a dirt bagel, basket is not full   | Show message saying type is invalid, return false |
|                            | Product::getVariant()                               |                  |                                                  |                                                   |
|                            |                                                     |                  |                                                  |                                                   |
|                            |                                                     |                  | I want to add an onion bagel, basket is full     | Show message saying basket is full, return false  |
|                            | Basket::addToBasket(Product bagel, int amount)      |                  | I want to add multiple onion bagels              | Add multiple of same bagel to basket              |

````dtd
2.
As a member of the public,
So I can change my order,
I'd like to remove a bagel from my basket.
````

ArrayList<Product> products

| Classes                    | Methods                                          | Member Variables | Scenario                                                                              | Output/Result                                        |
|----------------------------|--------------------------------------------------|------------------|---------------------------------------------------------------------------------------|------------------------------------------------------|
| Basket, Product, Inventory | Basket::removeFromBasket(String sku)             |                  | I want to remove an existing basket item                                              | Product is removed, return true                      |
|                            | Product::getSku()                                |                  |                                                                                       |                                                      |
|                            |                                                  |                  | I want to remove a non-existing item                                                  | Show message saying product not in basket            |
|                            | Basket::removeFromBasket(String sku, int amount) |                  | I want to remove a number of the same product. Equally or more products are in basket | Multiple of the same product is removed, return true |

````dtd
3.
As a member of the public,
So that I can not overfill my small bagel basket
I'd like to know when my basket is full when I try adding an item beyond my basket capacity.
````

ArrayList<Product> products
int size

| Classes                    | Methods                              | Member Variables | Scenario                                     | Output/Result                      |
|----------------------------|--------------------------------------|------------------|----------------------------------------------|------------------------------------|
| Basket, Product, Inventory | Basket::addToBasket(Product product) |                  | I want to add an onion bagel, basket is full | Show message to user, return false |
|                            |                                      |                  |                                              |                                    |

````dtd
4.
As a Bob's Bagels manager,
So that I can expand my business,
I’d like to change the capacity of baskets.
````

ArrayList<Product> products
int size

| Classes | Methods                      | Member Variables | Scenario                                                             | Output/Result                                     |
|---------|------------------------------|------------------|----------------------------------------------------------------------|---------------------------------------------------|
| Basket  | Basket::changeSize(int size) |                  | I want to change size from 10 to 20                                  | Max size is changed, return true                  |
|         |                              |                  | I want to change size from 20 to 10, all baskets are empty           | Max size is changed, return true                  |
|         |                              |                  | I want to change size from 20 to 10, there is a basket with 5 items  | Max size is changed, return true                  |
|         |                              |                  | I want to change size from 20 to 10, there is a basket with 15 items | Show error message, basket size remains unchanged |
|         |                              |                  | I want to change size from 20 to -5                                  | Show error message, basket size remains unchanged |

````dtd
5.
As a member of the public
So that I can maintain my sanity
I'd like to know if I try to remove an item that doesn't exist in my basket.
````

ArrayList<Product> products

| Classes         | Methods                              | Member Variables | Scenario                                             | Output/Result                      |
|-----------------|--------------------------------------|------------------|------------------------------------------------------|------------------------------------|
| Basket, Product | Basket::removeFromBasket(String sku) |                  | I want to remove an onion bagel, it is not in basket | Show message to user, return false |
|                 | Product::getSku()                    |                  |                                                      |                                    |

````dtd
6.
As a customer,
So I know how much money I need,
I'd like to know the total cost of items in my basket.
````

ArrayList<Product> products

| Classes | Methods            | Member Variables | Scenario                                                  | Output/Result                                                                                   |
|---------|--------------------|------------------|-----------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| Basket  | Basket::getTotal() |                  | I want to know the total, there are products in my basket | Calculate the total cost by getting price of products, and add the together and display to user |
|         |                    |                  | I want to know the total, my basket is empty              | Show message saying there are no products in basket                                             |

````dtd
7.
As a customer,
So I know what the damage will be,
I'd like to know the cost of a bagel before I add it to my basket.
````

| Classes         | Methods                                            | Member Variables | Scenario                                                       | Output/Result                   |
|-----------------|----------------------------------------------------|------------------|----------------------------------------------------------------|---------------------------------|
| Product, Basket | Basket::showPrices(Inventory), Product::getPrice() | double price     | I want to know the cost of all products                        | Show the price of all products  |
|                 | Basket::showPrices(String, Inventory)              |                  | I want to know the cost of a plain bagel                       | Show the price 0.39             |
|                 | Basket::showPrices(String[], Inventory)            |                  | I want to know the cost on a plain bagel and cappuccino coffee | Show the price of both products |

````dtd
8.
As a customer,
So I can shake things up a bit,
I'd like to be able to choose fillings for my bagel.
````

| Classes          | Methods                         | Member Variables                   | Scenario                                                               | Output/Result                                                                                   |
|------------------|---------------------------------|------------------------------------|------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| Product, Basket  | Basket::addToBasket(String sku) | Basket::ArrayList<Product>products | I want to add bacon to my basket, there are 1 or more bagels in basket | Add bacon to basket, return true                                                                |
|                  |                                 |                                    | I want to add bacon to my basket, there are no bagels in basket        | Show message asking for confirmation, if yes, add to basket. return true. If no, no action done |
|                  |                                 |                                    | I want to add invalid filling to my basket                             | Show a message saying filling not found                                                         |

````dtd
9.
As a customer,
So I don't over-spend,
I'd like to know the cost of each filling before I add it to my bagel order.
````

| Classes         | Methods              | Member Variables | Scenario                                      | Output/Result                           |
|-----------------|----------------------|------------------|-----------------------------------------------|-----------------------------------------|
| Product, Basket | Product::showPrice() | double price     | I want to know the cost of an cheese          | Show the price 0.12                     |
|                 |                      |                  | I want to know the cost of ham                | Show the price 0.12                     |
|                 |                      |                  | I want to know the cost on an invalid filling | Show a message saying filling not found |

````dtd
10.
As the manager,
So we don't get any weird requests,
I want customers to only be able to order things that we stock in our inventory.
````

| Classes           | Methods                               | Member Variables  | Scenario                                     | Output/Result                                             |
|-------------------|---------------------------------------|-------------------|----------------------------------------------|-----------------------------------------------------------|
| Inventory, Basket | Basket::addToBasket(Product product)  | Inventory::amount | Customer wants to order product in stock     | Add product as normal                                     |
|                   | Basket::checkStock(Inventory, String) |                   | Customer wants to order product out of stock | Show message saying product is out of stock, return false |


## Extension

````dtd
11.
As a manager,
So our prices will look cheaper and boost sales,
I want our customer to receive discounts for purchasing more of our products
````

double discount

| Classes           | Methods                              | Member Variables               | Scenario | Output/Result |
|-------------------|--------------------------------------|--------------------------------|----------|---------------|
| Basket, Inventory | Basket::getBagelDiscount()           |                                |          |               |
|                   | Basket::addToBasket(Product product) | priceDiscountNewPrice Double[] |          |               |

````dtd
12. 
As a customer,
So I can keep track of my spending,
I want to get a receipt for my purchase
````

| Classes         | Methods                                          | Member Variables              | Scenario                  | Output/Result                                              |
|-----------------|--------------------------------------------------|-------------------------------|---------------------------|------------------------------------------------------------|
| Basket, Receipt | Receipt::generateReceipt()                       | Basket basket                 | I have items in my basket | A receipt showing the prices of items and total is printed |
|                 | Receipt::getPadding()                            | StringBuilder receipt         |                           |                                                            |
|                 | Receipt::getDashedLine()                         | StringBuilder productString   |                           |                                                            |
|                 | Receipt::getDateTime()                           | String total                  |                           |                                                            |
|                 | Receipt::countProducts()                         | String numbers                |                           |                                                            |
|                 | Receipt::generateProductStrings()                | StringBuilder resultingString |                           |                                                            |
|                 | Receipt::generateTotalString()                   | int padding                   |                           |                                                            |
|                 | Receipt::generateMidSpacedString(String, String) |                               |                           |                                                            |

````dtd
13.
As a customer,
So I feel smart about my choices,
I want to know how much money I've saved on special offers
````
| Classes         | Methods                                          | Member Variables              | Scenario                                 | Output/Result                                                 |
|-----------------|--------------------------------------------------|-------------------------------|------------------------------------------|---------------------------------------------------------------|
| Basket, Receipt | Receipt::generateReceipt()                       | Basket basket                 | I have 6 bagels in my basket             | A receipt showing the ordinary price, and minus the discount  |
|                 | Receipt::getPadding()                            | StringBuilder receipt         | I have 12 bagels in my basket            | A receipt showing the ordinary price, and minus the discount  |
|                 | Receipt::getDashedLine()                         | StringBuilder productString   | I have a bagel and a coffee in my basket | A receipt showing the ordinary price, and minus the discount  |
|                 | Receipt::getDateTime()                           | String total                  |                                          |                                                               |
|                 | Receipt::countProducts()                         | String numbers                |                                          |                                                               |
|                 | Receipt::generateProductStrings()                | StringBuilder resultingString |                                          |                                                               |
|                 | Receipt::generateTotalString()                   | int padding                   |                                          |                                                               |
|                 | Receipt::generateMidSpacedString(String, String) |                               |                                          |                                                               |

````dtd
14.
As a customer,
So I know when I get my grub,
I want to receive a confirmation text message on my phone
````

| Classes     | Methods                                                           | Member Variables           | Scenario                      | Output/Result                                                                                       |
|-------------|-------------------------------------------------------------------|----------------------------|-------------------------------|-----------------------------------------------------------------------------------------------------|
| SMSOutbound | SMSOutbound::sendMessage(String messageBody)                      | StringBuilder orderSummary | There are items in the basket | A message is sent to the customer with a summary of the items, as well as an estimted delivery time |
|             | SMSOutbound::sendOrderMessage(HashMap<String, Int> productCounts) |                            |                               |                                                                                                     |
|             | Inventory::getName(String sku)                                    |                            |                               |                                                                                                     |
