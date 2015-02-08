package com.w3e.sharingmate.helper;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

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

    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                append(index).
                append(value).
                toHashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair))
            return false;
        if (obj == this)
            return true;

        Pair rhs = (Pair) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                        append(index, rhs.index).
                append(value, rhs.value).
                isEquals();
    }

    }
