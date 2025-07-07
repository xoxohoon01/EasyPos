package model;

import java.sql.Timestamp;

public class Sale
{
    private final int sale_id;
    private final int store_id;
    private final int product_id;
    private final int quantity;
    private Timestamp sale_date;

    public Sale(int sale_id, int store_id, int product_id, int quantity, Timestamp sale_date)
    {
        this.sale_id = sale_id;
        this.store_id = store_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.sale_date = sale_date;
    }

    public int getSale_id() { return sale_id; }
    public int getProduct_id() { return product_id; }
    public int getQuantity() { return quantity; }
    public Timestamp getSale_date() { return sale_date; }
}
