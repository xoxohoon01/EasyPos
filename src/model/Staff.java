package model;

public class Staff
{
    private final int staff_id;
    private final String name;

    public Staff(int staff_id, String name)
    {
        this.staff_id = staff_id;
        this.name = name;
    }

    public int getStaff_id() { return staff_id; }
    public String getStaff_name() { return name; }
}
