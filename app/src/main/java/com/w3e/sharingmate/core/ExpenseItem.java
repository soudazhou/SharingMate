package com.w3e.sharingmate.core;

import java.util.Date;

/**
 * Created by Wenxuan on 2014/11/27.
 */
public class ExpenseItem implements Comparable<ExpenseItem> {
    private double amount = 0;
    private Date date;

    public ExpenseItem(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(ExpenseItem o) {
        return getDate().compareTo(o.getDate());
    }
}
