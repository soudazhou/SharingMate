package com.w3e.sharingmate.core;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Wenxuan on 2014/11/27.
 */
public class ExpenseItem {
    private double amount;
    private ArrayList<String> paidWith;
    private Calendar dateTime;
    private String description;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<String> getPaidWith() {
        return paidWith;
    }

    public void setPaidWith(ArrayList<String> paidWith) {
        this.paidWith = paidWith;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
