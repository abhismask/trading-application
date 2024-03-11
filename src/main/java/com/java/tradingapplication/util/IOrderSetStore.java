package com.java.tradingapplication.util;


import com.java.tradingapplication.model.BuyOrderSet;
import com.java.tradingapplication.model.SellOrderSet;
import com.java.tradingapplication.model.Instrument;

import java.util.Map;

/**
 * Interface for persisting incoming orders based stock. For simplicity's sake,
 * just a map for this implementation
 */
public interface IOrderSetStore {
    public Map<Instrument, BuyOrderSet> getBuyOrderStore();

    public Map<Instrument, SellOrderSet> getSellOrderStore();
}