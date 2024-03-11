package com.java.tradingapplication.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderEntry {
    private UUID id;
    private Order party;
    private Order counterParty;
    private int quantity;
    private BigDecimal executionPrice;

    public OrderEntry(Order party, Order counterParty, int quantity, BigDecimal executionPrice) {
        this.id = UUID.randomUUID();
        this.party = party;
        this.counterParty = counterParty;
        this.quantity = quantity;
        this.executionPrice = executionPrice;
    }

    public UUID getId() {
        return id;
    }

    public Order getParty() {
        return this.party;
    }

    public Order getCounterParty() {
        return this.counterParty;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public BigDecimal getExecutionPrice() {
        return this.executionPrice;
    }


}