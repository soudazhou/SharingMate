package com.w3e.sharingmate;

import java.util.HashMap;

/**
 * Created by Wenxuan on 2014/11/27.
 */
public class ExpenseEntry {
    private HashMap<Integer,ExpenseItem> personIdToItem;

    public HashMap<Integer, ExpenseItem> getPersonIdToItem() {
        return personIdToItem;
    }

    public void setPersonIdToItem(HashMap<Integer, ExpenseItem> personIdToItem) {
        this.personIdToItem = personIdToItem;
    }

    //create Entry when the button is pressed

    //the intent would contain map<personId, ExpenseItem>
}
