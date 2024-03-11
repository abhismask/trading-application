package com.java.tradingapplication.util;

import com.java.tradingapplication.model.OrderEntry;

import java.util.ArrayList;
import java.util.List;

public final class TransactionStore implements ITransactionStore {
    private final List<OrderEntry> orderEntries;

    private TransactionStore() {
        orderEntries = new ArrayList<>();
    }

    private static class LazyHolder {
        private static final TransactionStore INSTANCE = new TransactionStore();
    }

    public static TransactionStore getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public List<OrderEntry> getOrderEntries() {
        return getInstance().orderEntries;
    }
}