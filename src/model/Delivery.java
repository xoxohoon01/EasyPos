package model;

import java.sql.Timestamp;

public class Delivery
{
    private final int store_id;
    private final int product_id;
    private final int quantity;
    private Timestamp delivery_date;
    private final int delivery_id;

    public Delivery(int store_id, int product_id, int quantity, Timestamp delivery_date, int delivery_id)
    {
        this.store_id = store_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.delivery_date = delivery_date;
        this.delivery_id = delivery_id;
    }
}
