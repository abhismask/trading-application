package com.java.tradingapplication.util;


import com.java.tradingapplication.model.OrderEntry;

import java.util.List;

public interface ITransactionStore {
    public List<OrderEntry> getOrderEntries();
}