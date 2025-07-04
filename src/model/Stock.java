package model;

import java.sql.Timestamp;

public class Stock
{
    private final int stock_id;
    private final int store_id;
    private final int product_id;
    private final int quantity;
    private Timestamp registered_date;
    private Timestamp expiration_date;

    public Stock(int stock_id, int store_id, int product_id, int quantity, Timestamp registered_date, Timestamp expiration_date)
    {
        this.stock_id = stock_id;
        this.store_id = store_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.registered_date = registered_date;
        this.expiration_date = expiration_date;
    }

    public int getStock_id() { return stock_id; }
    public int getStore_id() { return store_id; }
    public int getProduct_id() { return product_id; }
    public int getQuantity() { return quantity; }
    public Timestamp getRegistered_date() { return registered_date; }
    public Timestamp getExpiration_date() { return expiration_date; }
}
