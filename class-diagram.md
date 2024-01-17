````mermaid
classDiagram
    Basket : - product - ArrayList< Product >
    Basket : - size - int
    Basket : - total - double
    Basket : + addToBasket(Product) - boolean
    Basket : + addToBasket(Product, int) - boolean
    Basket : + removeFromBasket(Product) - boolean
    Basket : + removeFromBasket(Product, int) - boolean
    Basket : + changeSize(int) - boolean
    Basket : + getTotal() - Double
    Product : - name - String
    Product : - variant - String
    Product : - sku - String
    Product : - price - double
    Product : - inventory - Inventory
    Product : + getPrice() - Double
    Product : + getName() - String
    Product : + getSku() - String
    Product : + getVariant() - String
    Product : + showPrice() - void
    Inventory : - prices - Map
    Inventory : - stock - Map
    Inventory : - names - Map
    Inventory : - variants - Map
    Inventory : + restockAll() - boolean
    Inventory : + restock(String sku) - boolean
    Inventory : + changePrice(String sku, double price) - boolean
    Inventory : + getPrice(String sku) - Double
    Inventory : + getName(String sku) - String
    Inventory : + getVariant(String sku) - String
    Inventory : + getStock(String sku) - int

````