package com.java.tradingapplication.util;


import com.java.tradingapplication.model.OrderEntry;

import java.util.List;

/**
 * Interface for persisting order book containing executed orders. For
 * simplicity's sake, just a list for this implementation
 * 
 */
public interface ITransactionStore {
    public List<OrderEntry> getOrderEntries();
}