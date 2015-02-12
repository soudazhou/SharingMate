package com.w3e.sharingmate.core;

import com.w3e.sharingmate.helper.Gender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


/**
 * Created by Wenxuan on 2014/11/27.
 */
public class Person implements Serializable {
    private int id;
    private String name;
    private Gender gender;
    private ArrayList<ExpenseItem> expense;

    public Person() {
    }

    public Person(int id, String name, ArrayList<ExpenseItem> expense) {
        this.id = id;
        this.name = name;
        this.expense = expense;
    }

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
