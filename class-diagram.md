````mermaid
classDiagram
    Basket : - product - ArrayList< Product >
    Basket : - size - int
    Basket : + addProduct(Product) - boolean
    Basket : + addProduct(Product, int) - boolean
    Basket : + removeProduct(Product) - boolean
    Basket : + removeProduct(Product, int) - boolean
    Basket : + changeCapacity(int) - boolean
    Basket : + getTotal() - Double
    Product : - name - String
    Product : - sku - String
    Product : + getPrice() - Double
    Product : + showPrice() - void
    Inventory : - productPrices - Map
    Inventory : - productInventory - Map
    Inventory : + restockAll() - boolean
    Inventory : + restock(String sku) - boolean
    Inventory : + changePrice(String sku, double price) - boolean
    Inventory : + getPrice(String sku) - Double

````