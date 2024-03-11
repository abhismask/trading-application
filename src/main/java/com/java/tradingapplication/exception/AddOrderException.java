package com.java.tradingapplication.service;

/**
 * AddOrderException may be thrown while trying to add an order to
 * {@link com.sample.stockexchange.entity.BuyOrderSet}
 */
public class AddOrderException extends Exception {

    public AddOrderException(String msg) {
        super(msg);
    }
}