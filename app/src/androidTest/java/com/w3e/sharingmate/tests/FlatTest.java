package com.w3e.sharingmate.tests;

import android.test.InstrumentationTestCase;
import com.w3e.sharingmate.core.*;
import com.w3e.sharingmate.helper.Pair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * Created by Wenxuan on 2014/12/2.
 */
public class FlatTest extends InstrumentationTestCase {
    Flat mFlat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void setUp() throws Exception {
        try {

            super.setUp();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Person eddie = new Person();
        Person william = new Person();
        Person wen = new Person();
        eddie.setId(1);
        william.setId(2);
        wen.setId(3);
        eddie.setName("Eddie");
        william.setName("William");
        wen.setName("Wen");

        ArrayList<ExpenseItem> eddieList = new ArrayList<ExpenseItem>(5);
        eddieList.add(new ExpenseItem(sdf.parse("01/12/2014"), 3));
        eddieList.add(new ExpenseItem(sdf.parse("02/12/2014"), 2));
        eddieList.add(new ExpenseItem(sdf.parse("03/12/2014"), 1));
        eddieList.add(new ExpenseItem(sdf.parse("04/12/2014"), 2));
        eddieList.add(new ExpenseItem(sdf.parse("05/12/2014"), 5));

        ArrayList<ExpenseItem> williamList = new ArrayList<ExpenseItem>(5);
        williamList.add(new ExpenseItem(sdf.parse("01/12/2014"), 2));
        williamList.add(new ExpenseItem(sdf.parse("02/12/2014"), 3));
        williamList.add(new ExpenseItem(sdf.parse("03/12/2014"), 4));
        williamList.add(new ExpenseItem(sdf.parse("04/12/2014"), 2));
        williamList.add(new ExpenseItem(sdf.parse("05/12/2014"), 0));

        ArrayList<ExpenseItem> wenList = new ArrayList<ExpenseItem>(5);
        wenList.add(new ExpenseItem(sdf.parse("01/12/2014"), 0));
        wenList.add(new ExpenseItem(sdf.parse("02/12/2014"), 5));
        wenList.add(new ExpenseItem(sdf.parse("03/12/2014"), 3));
        wenList.add(new ExpenseItem(sdf.parse("04/12/2014"), 10));
        wenList.add(new ExpenseItem(sdf.parse("05/12/2014"), 0));

        eddie.setExpense(eddieList);
        william.setExpense(williamList);
        wen.setExpense(wenList);

        ArrayList<Person> roommates = new ArrayList<Person>();
        roommates.add(eddie);
        roommates.add(william);
        roommates.add(wen);
        mFlat = new Flat();
        mFlat.setRoommates(roommates);
    }

    @Override
    protected void tearDown() {
        try {
            super.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void testGeneratePidBalancePair() throws Exception {

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Pair> expectedPair = new ArrayList<Pair>();
        expectedPair.add(new Pair(1, -1));
        expectedPair.add(new Pair(2, -3));
        expectedPair.add(new Pair(3, 4));

        ArrayList<Pair> flatExpense = mFlat.generatePidBalancePair(sdf.parse("01/12/2014"), sdf.parse("05/12/2014"));
        assertEquals(expectedPair, flatExpense);
        String settlement = "<<<<Settlement Instruction<<<<\n\nWilliam should pay Wen 3.0\nEddie should pay Wen 1.0";
        String generatedString = mFlat.generateSettlementInstruction(flatExpense);
        assertEquals(settlement, generatedString);

    }
}