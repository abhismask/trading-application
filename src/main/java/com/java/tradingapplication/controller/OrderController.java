package com.java.tradingapplication.controller;


import com.java.tradingapplication.model.CompositeOrder;
import com.java.tradingapplication.model.Order;
import com.java.tradingapplication.model.OrderEntry;
import com.java.tradingapplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(value = "/instrument")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place-order")
    String placeOrder(@RequestBody Order order) {
        if (order == null) {
            return "Order cannot be placed with empty order details";
        }
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orderService.placeOrder(orders);
        return "Order placed for orderId: " + order.getId();
    }

    @PostMapping("/place-composite-order")
    String placeCompositeOrder(@RequestBody CompositeOrder compositeOrder) {
        if (compositeOrder == null || compositeOrder.getOrders().length == 0) {
            return "Order cannot be placed with empty order details";
        }
        if (compositeOrder.getOrders().length > 3) {
            return "Maximum 3 composite orders are supported";
        }
        if (compositeOrder.getOrders().length > 0) {
            List<Order> orderList = new ArrayList<>(Arrays.asList(compositeOrder.getOrders()));
            orderService.placeOrder(orderList);
        }
        return "Order placed for orderId: " + compositeOrder.getId();
    }

    @PostMapping("/cancel-order")
    String cancelOrder(@RequestBody Order order) {
        if (order == null) {
            return "Order cannot be cancelled with empty order details";
        }

        boolean isCancelled = orderService.cancel(order);
        if (isCancelled) {
            return "Order cancelled for orderId: " + order.getId();
        } else {
            return "Order cannot be cancelled for orderId: " + order.getId();
        }


    }

    @GetMapping("/transactions")
    List<OrderEntry> getAllTransaction() {
        return orderService.getAllTransactionDetails();
    }

    @GetMapping("/orders")
    List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

}