package com.w3e.sharingmate.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Wenxuan on 2014/11/27.
 */
public class Person {
    private int id;
    private String name;
    private boolean isMale;
    private ArrayList<ExpenseItem> expense;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    public ArrayList<ExpenseItem> getExpense() {
        return expense;
    }

    public void setExpense(ArrayList<ExpenseItem> expense) {
        this.expense = expense;
    }

    public ArrayList<ExpenseItem> getExpenseListWithinPeriod(Date start, Date end) {
        ArrayList<ExpenseItem> expenseWithinPeriod = new ArrayList<ExpenseItem>();
        for(ExpenseItem expenseItem : expense) {
            if(expenseItem.getDate().compareTo(start) >= 0) {
                if(expenseItem.getDate().compareTo(end) <= 0) {
                    expenseWithinPeriod.add(expenseItem);
                }
            }
        }
        Collections.sort(expenseWithinPeriod);
        return expenseWithinPeriod;
    }

    public double getTotalExpenseWithinPeriod(Date start, Date end) {
        ArrayList<ExpenseItem> expenseWithinPeriod = getExpenseListWithinPeriod(start, end);
        double totalExpense = 0;
        for(ExpenseItem e : expenseWithinPeriod) {
            totalExpense += e.getAmount();
        }
        return totalExpense;
    }
}
