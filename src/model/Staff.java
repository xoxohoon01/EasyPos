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

    public int getStaffID() { return staff_id; }
    public String getStaffName() { return name; }
}
