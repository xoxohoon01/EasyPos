package model;

import java.sql.Timestamp;

public class Work
{
    private final int work_id;
    private final int store_id;
    private final int staff_id;
    private final Timestamp log_date;
    private final String cause;

    public Work(int work_id, int store_id, int staff_id, Timestamp log_date, String cause)
    {
        this.work_id = work_id;
        this.store_id = work_id;
        this.staff_id = staff_id;
        this.log_date = log_date;
        this.cause = cause;
    }

    public int getWork_id() { return work_id; }
    public int getStore_id() { return store_id; }
    public int getStaff_id() { return staff_id; }
    public Timestamp getLog_date() { return log_date; }
    public String getCause() { return cause; }
}
