package com.java.tradingapplication.model;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Order {
    private String id;

    private String traderId;
    private LocalTime time;
    private OrderType type;
    private int quantity;
    private Instrument instrument;
    private BigDecimal askingPrice;

    private boolean isCancelled;

    public Order() {

    }

    public Order(String id, String traderId, LocalTime time, OrderType type, int quantity, Instrument instrument, BigDecimal askingPrice) {
        this.id = id;
        this.traderId = traderId;
        this.time = time;
        this.type = type;
        this.quantity = quantity;
        this.instrument = instrument;
        this.askingPrice = askingPrice;
    }

    public BigDecimal getAskingPrice() {
        return askingPrice;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Order) && (this.id.equals(((Order) obj).getId()));
    }

}