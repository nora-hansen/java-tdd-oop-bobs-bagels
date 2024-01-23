````mermaid
classDiagram
    class Basket{    
        - product : ArrayList < Product >
        - inventory : Inventory
        - size : int
        - total : double
        - discountedItems : HashMap< String, Double[] >
        - productCounts HashMap< String, Integer >
        + addToBasket(Product) boolean
        + addToBasket(Product, int) boolean
        + removeFromBasket(Product) boolean
        + removeFromBasket(Product, int) boolean
        + changeSize(int) boolean
        + getTotal() Double
        + showPrices() void
        + showPrices(String) void
        + showPrices(String[]) void
        + getBagelDiscount() : double
        + getDiscountedItems() : double
        }
    class Product{    
        - name : String
        - variant : String
        - sku : String
        - price : double
        - inventory : Inventory
        + getPrice() Double
        + getName() String
        + getSku() String
        + getVariant() String
        + showPrice() void
    }
    class Coffee {
            
        }
    class Bagel {

    }
    class Filling {

    }
    class Inventory{    
        - prices : HashMap< String, Double >
        - stock : HashMap< String, Integer >
        - names : HashMap< String, String >
        - variants : HashMap< String, String >
        + restockAll() : boolean
        + restock(String sku) : boolean
        + changePrice(String sku, double price) : boolean
        + getPrice(String sku) : Double
        + getName(String sku) : String
        + getVariant(String sku) : String
        + getStock(String sku) : int
        + setStock(String sku, int amount) : void
        + getProductString(String sku) : String
        }
    class BobsBagels{
        - mainMenu : String
        - managerMenu : String
        - shopMenu : String
        - sc : Scanner
        - inventory : Inventory
        - basket : Basket
        + showMenu() : void
        + showManagerMenu() : void
        + showShopMenu() : void
    }
    class Receipt{
        - receipt : String
        - basket : Basket
        - date : LocalDate
        - time : LocalTime
        - format : DateTimeFormatter
        - topText : String
        - bottomText : String[]
        - productCounts : HashMap< String, Integer >
        - productDiscounts : HashMap< String, Double >
        - width : int
        + generateReceipt() : String
        + getPadding(String) : int
        + getDashedLine : String
        + getDateTime : String
        + generateProductStrings : void
    }
    class SMSOutbound{
        - ACCOUNT_SID : String
        - AUTH_TOKEN : String
        - NUMBER : String
        - TWILIO_NUMBER : String
        - inventory : INVENTORY
        - random : Random
        + init() : void
        + sendMessage(String) : void
        + sendOrderMessage(HashMap< String, Integer >) : void
    }
    Basket ..> Product : 0..*
    Product ..> Inventory : 1..1
    BobsBagels ..> Basket
    BobsBagels ..> Inventory
    Receipt ..> Basket : 1..1
    Product ..|> Bagel
    Product ..|> Coffee
    Product ..|> Filling
    Inventory ..> SMS
````