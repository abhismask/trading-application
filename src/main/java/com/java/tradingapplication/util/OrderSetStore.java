package com.java.tradingapplication.util;


import com.java.tradingapplication.model.BuyOrderSet;
import com.java.tradingapplication.model.SellOrderSet;
import com.java.tradingapplication.model.Instrument;

import java.util.HashMap;
import java.util.Map;

public final class OrderSetStore implements IOrderSetStore {
    private final HashMap<Instrument, BuyOrderSet> buyMap;
    private final HashMap<Instrument, SellOrderSet> sellMap;

    private OrderSetStore() {
        buyMap = new HashMap<>();
        sellMap = new HashMap<>();
    }

    private static class LazyLoader {
        private static final OrderSetStore INSTANCE = new OrderSetStore();
    }

    public static OrderSetStore getInstance() {
        return LazyLoader.INSTANCE;
    }

    @Override
    public Map<Instrument, BuyOrderSet> getBuyOrderStore() {
        return getInstance().buyMap;
    }

    @Override
    public Map<Instrument, SellOrderSet> getSellOrderStore() {
        return getInstance().sellMap;
    }
}