package com.java.tradingapplication.service;


import com.java.tradingapplication.util.IOrderSetStore;
import com.java.tradingapplication.util.ITransactionStore;
import com.java.tradingapplication.util.OrderSetStore;
import com.java.tradingapplication.util.TransactionStore;
import com.java.tradingapplication.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.java.tradingapplication.model.OrderType.BUY;
import static com.java.tradingapplication.model.OrderType.SELL;
@Service
public final class OrderService {
    private  IOrderSetStore orderStore = OrderSetStore.getInstance();
    private ITransactionStore transactionStore = TransactionStore.getInstance();
    private  Map<Instrument, BuyOrderSet> buys = orderStore.getBuyOrderStore();
    private  Map<Instrument, SellOrderSet> sells = orderStore.getSellOrderStore();
    private  List<OrderEntry> transactionList= transactionStore.getOrderEntries();

    private List<Order> orderList = new ArrayList<>();

    public OrderService() {

    }

    public void addOrders(List<Order> orders) throws AddOrderException {
        if (orders == null || orders.isEmpty()) {
            return;
        }

        for (Order order : orders) {
            if (order == null) {
                continue;
            }

            if (order.getInstrument() == null) {
                throw new AddOrderException("No stocks attached to Order: " + order.getId());
            }

            Set<Order> orderSet = null;
            orderList.add(new Order(order.getId(), order.getTime(), order.getType(), order.getQuantity(), order.getInstrument(), order.getAskingPrice()));
            if (order.getType() == BUY) {
                BuyOrderSet buyOrders = buys.get(order.getInstrument());
                if (buyOrders == null) {
                    buyOrders = new BuyOrderSet();
                    buys.put(order.getInstrument(), buyOrders);
                }

                orderSet = buyOrders.getOrderSet();
            } else if (order.getType() == SELL) {
                SellOrderSet sellOrders = sells.get(order.getInstrument());
                if (sellOrders == null) {
                    sellOrders = new SellOrderSet();
                    sells.put(order.getInstrument(), sellOrders);
                }
                //orderList.add(new Order(order.getId(), order.getTime(), order.getType(), order.getQuantity(), order.getStock(), order.getAskingPrice()));
                orderSet = sellOrders.getOrderSet();
            }

            if (orderSet.contains(order)) {
                throw new AddOrderException("Order is possibly duplicated: " + order.getId());
            } else {
                orderSet.add(order);
                System.out.println(orderSet);
            }
        }
    }

    /**
     * Cleans in-memory data-stores. Useful for testing.
     */
    public void cleanup() {
        buys.clear();
        sells.clear();
        transactionList.clear();
    }
    public  void processOrders() {
        // process buy orders
        buys.forEach((stock, orders) -> {
            Set<Order> buyOrderSet = orders.getOrderSet();

            if (buyOrderSet == null || buyOrderSet.isEmpty()) {
                return;
            }

            SellOrderSet sOrderSet = sells.get(stock);
            if (sOrderSet == null) {
                return;
            }

            Set<Order> sellOrderSet = sOrderSet.getOrderSet();

            buyOrderSet.stream().filter(order -> (order.getQuantity() > 0)).forEach((buy) -> {
                for (Order sell : sellOrderSet) {
                    if (sell.getQuantity() > 0 && buy.getAskingPrice().compareTo(sell.getAskingPrice()) >= 0) {

                        int qty = 0;
                        if (sell.getQuantity() > buy.getQuantity()) {
                            qty = buy.getQuantity();
                            sell.setQuantity(sell.getQuantity() - buy.getQuantity());
                            buy.setQuantity(0);
                        } else {
                            qty = sell.getQuantity();
                            buy.setQuantity(buy.getQuantity() - sell.getQuantity());
                            sell.setQuantity(0);
                        }

                        // record it in order entry

                        OrderEntry entry = new OrderEntry(sell, buy, qty, sell.getAskingPrice());
                        transactionList.add(entry);
                        System.out.println(transactionList);
                        System.out.println("***************************************************");
                        System.out.println("SellMap: "+sells);
                        System.out.println("BuyMap "+buys);
                    }
                }
            });
        });
    }

    public void placeOrder(List<Order> orderList) {
        try {
            addOrders(orderList);
            processOrders();
        } catch (AddOrderException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderEntry> getAllTransactionDetails() {
        return transactionList;
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public boolean cancel(Order order) {
        Instrument removeInstrument = order.getInstrument();
        boolean isCancelled = false;
        if(order.getType().equals(BUY)){
            isCancelled =  buys.get(removeInstrument).getOrderSet().remove(order);
        }else{
            isCancelled = sells.get(removeInstrument).getOrderSet().remove(order);
        }
        if(isCancelled){
            order.setCancelled(true);
            orderList.add(order);
        }
        return isCancelled;
    }

}