package com.java.tradingapplication.model;

import java.util.Arrays;

public class CompositeOrder {
    private  String id;
    private Order orders[] = new Order[3];

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        for (Order order: orders){
            order.setId(id + "#" + order.getId());
        }
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CompositeOrder{" +
                "id='" + id + '\'' +
                ", orders=" + Arrays.toString(orders) +
                '}';
    }
}
