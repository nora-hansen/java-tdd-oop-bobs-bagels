````mermaid
classDiagram
    Basket : - product - ArrayList< Product >
    Basket : - size - int
    Basket : + addToBasket(Product) - boolean
    Basket : + addToBasket(Product, int) - boolean
    Basket : + removeFromBasket(Product) - boolean
    Basket : + removeFromBasket(Product, int) - boolean
    Basket : + changeCapacity(int) - boolean
    Basket : + getTotal() - Double
    Product : - name - String
    Product : - type - String
    Product : - sku - String
    Product : + getPrice() - Double
    Product : + showPrice() - void
    Inventory : - prices - Map
    Inventory : - stock - Map
    Inventory : - names - Map
    Inventory : - types - Map
    Inventory : + restockAll() - boolean
    Inventory : + restock(String sku) - boolean
    Inventory : + changePrice(String sku, double price) - boolean
    Inventory : + getPrice(String sku) - Double

````