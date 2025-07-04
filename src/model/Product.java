package model;

public class Product
{
    private final int product_id;
    private final String name;
    private final String category;
    private final int price;
    private final String isAdult;
    private final String company;

    public Product(int product_id, String name, String category, int price, String isAdult, String company)
    {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.isAdult = isAdult;
        this.company = company;
    }

    public int getProduct_id() { return product_id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getPrice() { return price; }
    public String getIsAdult() { return isAdult; }
    public String getCompany() { return company; }
}
