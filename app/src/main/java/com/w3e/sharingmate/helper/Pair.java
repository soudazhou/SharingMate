package com.w3e.sharingmate.helper;

/**
 * Created by Wenxuan on 2014/11/28.
 */
    public class Pair implements Comparable<Pair> {
        public final int index;
        public double value;

        public Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Pair other) {
            //multiplied to -1 as the author need descending sort order
            return -1 * Double.valueOf(this.value).compareTo(other.value);
        }
    }
