package com.w3e.sharingmate.core;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by Wenxuan on 2014/11/27.
 */
public class ExpenseEntry {
    private Date entryDate;
    private LinkedHashMap<Integer,Double> personIdToAmount;

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public HashMap<Integer, Double> getPersonIdToAmount() {
        return personIdToAmount;
    }

    public void setPersonIdToAmount(LinkedHashMap<Integer, Double> personIdToAmount) {
        this.personIdToAmount = personIdToAmount;
    }

    //todo: discuss whether "totalsharedexpense" input from user is needed, if so need to check before submit entry
    //validate the entry before send
    public double getSumofIndividualPaid() {
        double sum = 0;
        Iterator<Double> it = personIdToAmount.values().iterator();
        while (it.hasNext())
        {
            Double individualPaid = it.next();
            sum += individualPaid;
        }
        return sum;
    }

//create Entry when the button is pressed
//the intent would contain map<personId, ExpenseItem>
}
