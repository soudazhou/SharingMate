package com.w3e.sharingmate.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.w3e.sharingmate.R;
import com.w3e.sharingmate.core.ExpenseItem;
import com.w3e.sharingmate.core.Flat;
import com.w3e.sharingmate.core.Person;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends Activity {
    public final static String MY_FLAT = "com.w3e.sharingmate.MYFLAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //called when "addEntry" button is clicked. links to  <android:onClick="addEntry" /> in res/layout/activity_main.xml
    public void addEntry() {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        //todo: create a "pop-out diaoglue box to enable users input for entry"
        //construct ExpenseEntry with the input box contents(personid -> expenseItem), pass it to SettlementInstructionActivity via intent
    }

    //called when 'settle' button is clicked.
    public void onSettle(View view) {
        Intent intent = new Intent(this, SettlementInstructionActivity.class);
        Flat mFlat = new Flat();
        ArrayList<Person> roommates = new ArrayList<Person>();

        String textViewName1 = ((EditText) findViewById(R.id.textViewName1)).getText().toString();
        String textViewName2 = ((EditText) findViewById(R.id.textViewName2)).getText().toString();
        String textViewName3 = ((EditText) findViewById(R.id.textViewName3)).getText().toString();

        EditText textAmount1 = (EditText) findViewById(R.id.textAmount1);
        EditText textAmount2 = (EditText) findViewById(R.id.textAmount2);
        EditText textAmount3 = (EditText) findViewById(R.id.textAmount3);

        double amountPaid1 = getAmountDouble(textAmount1);
        double amountPaid2 = getAmountDouble(textAmount2);
        double amountPaid3 = getAmountDouble(textAmount3);

        ArrayList<ExpenseItem> item1 = new ArrayList<ExpenseItem>(Arrays.asList(new ExpenseItem(amountPaid1)));
        ArrayList<ExpenseItem> item2 = new ArrayList<ExpenseItem>(Arrays.asList(new ExpenseItem(amountPaid2)));
        ArrayList<ExpenseItem> item3 = new ArrayList<ExpenseItem>(Arrays.asList(new ExpenseItem(amountPaid3)));

        roommates.add(new Person(1, textViewName1, item1));
        roommates.add(new Person(2, textViewName2, item2));
        roommates.add(new Person(3, textViewName3, item3));
        mFlat.setRoommates(roommates);
        intent.putExtra(MY_FLAT, mFlat);
        startActivity(intent);
    }

    private double getAmountDouble(EditText textAmount) {
        String textAmountString = textAmount.getText().toString();
        if (textAmountString == null || textAmountString.isEmpty())
            return 0.0;
        else
            return Double.parseDouble(textAmountString);
    }
    //public ArrayList<Pair> setNameAmountPair(ArrayList<String> names, ArrayList<>)
}
