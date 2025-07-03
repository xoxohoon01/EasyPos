package model;

public class Store
{
    private final int store_id;
    private final String store_name;
    public Store(int store_id, String store_name)
    {
        this.store_id = store_id;
        this.store_name = store_name;
    }

    public int getStore_id() { return store_id; }
    public String getStore_name() { return store_name; }
}
