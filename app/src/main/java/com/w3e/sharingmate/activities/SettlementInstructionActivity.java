package com.w3e.sharingmate.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.w3e.sharingmate.R;
import com.w3e.sharingmate.core.Flat;
import com.w3e.sharingmate.helper.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class SettlementInstructionActivity extends Activity {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settlementinstruction);

        // Get the message from the intent
        Intent intent = getIntent();
        Flat mFlat = (Flat) intent.getSerializableExtra(MainActivity.MY_FLAT);
        ArrayList<Pair> flatExpense = new ArrayList<Pair>();
        try {
            flatExpense = mFlat.generatePidBalancePair(sdf.parse("01/12/2014"), sdf.parse("05/12/2014"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String generatedInstruction = mFlat.generateSettlementInstruction(flatExpense);
        // Create the text view
        TextView text_settlementInstruction = new TextView(this);
        text_settlementInstruction.setTextSize(40);
        text_settlementInstruction.setText(generatedInstruction);

        // Set the text view as the activity layout
        setContentView(text_settlementInstruction);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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

    //called when "remindMeLater" button is clicked. links to  <android:onClick="addEntry" /> in res/layout/activity_main.xml
    public void remindMeLater() {

        //todo: find out sample code interacting with google calendar"

    }

}
