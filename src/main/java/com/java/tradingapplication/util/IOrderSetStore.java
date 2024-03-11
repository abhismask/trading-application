package com.java.tradingapplication.util;


import com.java.tradingapplication.model.BuyOrderSet;
import com.java.tradingapplication.model.Instrument;
import com.java.tradingapplication.model.SellOrderSet;

import java.util.Map;

public interface IOrderSetStore {
    public Map<Instrument, BuyOrderSet> getBuyOrderStore();

    public Map<Instrument, SellOrderSet> getSellOrderStore();
}