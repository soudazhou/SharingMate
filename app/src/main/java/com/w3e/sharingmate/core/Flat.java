package com.w3e.sharingmate.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.w3e.sharingmate.helper.Pair;

/**
 * Created by Wenxuan on 2014/11/27.
 */
public class Flat {
    private ArrayList<Person> roommates;
    public ArrayList<Person> getRoommates() {
        return roommates;
    }

    private Map<Integer, String> generatePersonidToName() {
        Map<Integer, String> personidToName = new HashMap<Integer, String>();
        for(Person p : roommates) {
            personidToName.put(p.getId(), p.getName());
        }
        return personidToName;
    }

    //update flat status from the input of Mainactivity - addentry
    //todo
    public void updateRoomate() {

        //print success message
    }

    public double getTotalSharedExpenseWithinPeriod(Date start, Date end) {
        double totalExpense = 0;
        for(Person p : roommates) {
            totalExpense += p.getTotalExpenseWithinPeriod(start, end);
        }
        return totalExpense;
    }

    //create PidBalancePair for generating SettlementInstruction
    public ArrayList<Pair> generatePidBalancePair(Date start, Date end) {
        ArrayList<Pair> pidBalancePair = new ArrayList<Pair>();
        double totalSharedExpense = getTotalSharedExpenseWithinPeriod(start, end);
        double averageExpense = totalSharedExpense / roommates.size();
        for(Person p : roommates) {
            pidBalancePair.add(new Pair(p.getId(),p.getTotalExpenseWithinPeriod(start, end) - averageExpense));
        }
        return pidBalancePair;
    }

    public String generateSettlementInstruction(ArrayList<Pair> personidAndbalance) {
        int breakpoint = 0;
        StringBuilder settlementRecord = new StringBuilder("<<<<Settlement Instruction<<<<\n");
        Map<Integer, String> personidToName = generatePersonidToName();
        Collections.sort(personidAndbalance);
        for(int i = 0; i < personidAndbalance.size(); i++)
            if (personidAndbalance.get(i).value < 0) {
                breakpoint = i;
                break;
            }
        ArrayList<Pair> pos = new ArrayList<Pair>(personidAndbalance.subList(0 , breakpoint - 1));
        ArrayList<Pair> neg = new ArrayList<Pair>(personidAndbalance.subList(breakpoint , personidAndbalance.size() - 1));
        ArrayList<Pair> posUpdated = (ArrayList<Pair>) pos.clone();
        ArrayList<Pair> negUpdated = (ArrayList<Pair>) neg.clone();
        Collections.reverse(neg);
        for(int i = 0; i < pos.size(); i++) {
            for(int j = 0; j < neg.size(); j++) {
                String overPaidPersonName = personidToName.get(pos.get(i).index);
                String underPaidPersonName = personidToName.get(neg.get(j).index);
                double overPaidAmount = pos.get(i).value;
                double underPaidAmount = -1 * neg.get(j).value;
                Pair posUpdateditemPosRemain = new Pair(pos.get(i).index, posUpdated.get(i).value + negUpdated.get(j).value);
                Pair negUpdateditemPosRemain = new Pair(neg.get(j).index, 0);
                Pair posUpdateditemNegRemain = new Pair(pos.get(i).index, 0);
                Pair negUpdateditemNegRemain = new Pair(neg.get(j).index, posUpdated.get(i).value + negUpdated.get(j).value);

                if(pos.get(i).value + neg.get(j).value > 0) {
                    settlementRecord.append("\n" + underPaidPersonName + " should pay " + overPaidPersonName + "  " + underPaidAmount);
                    posUpdated.set(i, posUpdateditemPosRemain);
                    negUpdated.set(j, negUpdateditemPosRemain);
                }
                else {
                    settlementRecord.append("\n" + underPaidPersonName + " should pay " + overPaidPersonName + "  " + -1 * overPaidAmount);
                    posUpdated.set(i, posUpdateditemNegRemain);
                    negUpdated.set(j, negUpdateditemNegRemain);
                    break;//move pointer in pos to the next position
                }
            }
        }
        return settlementRecord.toString();
    }

/*
    public ArrayList<Pair> generatePidBalancePair(Date start, Date end) {
        ArrayList<Pair> pidBalancePair = new ArrayList<Pair>();
        double totalSharedExpense = getTotalSharedExpenseWithinPeriod(start, end);
        double averageExpense = totalSharedExpense / roommates.size();
        for(Person p : roommates) {
            pidBalancePair.add(new Pair(p.getId(),p.getTotalExpenseWithinPeriod(start, end) - averageExpense));
        }
        return pidBalancePair;
    }

    public String generateSettlementInstruction(ArrayList<Pair> personidAndbalance) {
        int breakpoint = 0;
        StringBuilder settlementRecord = new StringBuilder("<<<<Settlement Instruction<<<<\n");
        generatePersonidToName();
        Collections.sort(personidAndbalance);
        for(int i = 0; i < personidAndbalance.size(); i++)
            if (personidAndbalance.get(i).value < 0) {
                breakpoint = i;
                break;
            }
        ArrayList<Pair> pos = new ArrayList<Pair>(personidAndbalance.subList(0 , breakpoint - 1));
        ArrayList<Pair> neg = new ArrayList<Pair>(personidAndbalance.subList(breakpoint , personidAndbalance.size() - 1));
        ArrayList<Pair> posUpdated = (ArrayList<Pair>) pos.clone();
        ArrayList<Pair> negUpdated = (ArrayList<Pair>) neg.clone();
        Collections.reverse(neg);
        for(int i = 0; i < pos.size(); i++) {
            for(int j = 0; j < neg.size(); j++) {
                String overPaidPersonName = personidToName.get(pos.get(i).index);
                String underPaidPersonName = personidToName.get(neg.get(j).index);
                double overPaidAmount = pos.get(i).value;
                double underPaidAmount = -1 * neg.get(j).value;
                Pair posUpdateditemPosRemain = new Pair(pos.get(i).index, posUpdated.get(i).value + negUpdated.get(j).value);
                Pair negUpdateditemPosRemain = new Pair(neg.get(j).index, 0);
                Pair posUpdateditemNegRemain = new Pair(pos.get(i).index, 0);
                Pair negUpdateditemNegRemain = new Pair(neg.get(j).index, posUpdated.get(i).value + negUpdated.get(j).value);

                if(pos.get(i).value + neg.get(j).value > 0) {
                    settlementRecord.append("\n" + underPaidPersonName + " should pay " + overPaidPersonName + "  " + underPaidAmount);
                    posUpdated.set(i, posUpdateditemPosRemain);
                    negUpdated.set(j, negUpdateditemPosRemain);
                }
                else {
                    settlementRecord.append("\n" + underPaidPersonName + " should pay " + overPaidPersonName + "  " + -1 * overPaidAmount);
                    posUpdated.set(i, posUpdateditemNegRemain);
                    negUpdated.set(j, negUpdateditemNegRemain);
                    break;//move pointer in pos to the next position
                }
            }
        }
        return settlementRecord.toString();
    }*/

}
