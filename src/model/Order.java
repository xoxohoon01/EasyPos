package model;

import java.sql.Timestamp;

public class Order
{
    private final int order_id;
    private final int store_id;
    private final int product_id;
    private int quantity;
    private Timestamp order_date;

    public Order(int order_id, int store_id, int product_id, int quantity)
    {
        this.order_id = order_id;
        this.store_id = store_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getOrder_id() { return order_id; }
    public int getStore_id() { return store_id; }
    public int getProduct_id() { return product_id; }
    public int getQuantity() { return quantity; }
    public Timestamp getOrder_date() { return order_date; }

    public void setQuantity(int amount) { quantity = amount; }
    public void addQuantity(int amount) { quantity += amount; }
    public void minusQuantity(int amount) { quantity -= amount; }
}
