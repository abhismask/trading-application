package com.java.tradingapplication.model;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * SellOrderSet is a set of {@link Order} of type SELL, sorted by price.
 */
public class SellOrderSet {
    private SortedSet<Order> orderSet;

    public SellOrderSet() {
        Comparator<Order> comparator = new SellOrderComparator();
        this.orderSet = new TreeSet<>(comparator);
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    @Override
    public String toString() {
        return "SellOrderSet{" +
                "orderSet=" + orderSet +
                '}';
    }
}

final class SellOrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        if (a.getId().equals(b.getId())) {
            return 0; // invalid orders
        }
        int priceCompare = 1;
        if(a.getInstrument().getAskingPrice() !=null && b.getInstrument().getAskingPrice() != null){
            priceCompare = a.getInstrument().getAskingPrice().compareTo(b.getInstrument().getAskingPrice());
        }
        if (priceCompare == 0) {
            // A tie on price, check time
            return a.getTime().compareTo(b.getTime());
        }
        return priceCompare;
    }
}