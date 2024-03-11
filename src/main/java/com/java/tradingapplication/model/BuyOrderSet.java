package com.java.tradingapplication.model;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class BuyOrderSet {
    private SortedSet<Order> orderSet;

    public BuyOrderSet() {
        Comparator<Order> comparator = new BuyOrderComparator();
        this.orderSet = new TreeSet<>(comparator);
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    @Override
    public String toString() {
        return "BuyOrderSet{" +
                "orderSet=" + orderSet +
                '}';
    }
}

final class BuyOrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order a, Order b) {
        if (a.getId().equals(b.getId())) {
            return 0; // invalid orders
        }

        int timeCompare = a.getTime().compareTo(b.getTime());
        if (timeCompare == 0) {
            // A tie on time, check Id
            return a.getId().compareTo(b.getId());
        }
        return timeCompare;
    }
}