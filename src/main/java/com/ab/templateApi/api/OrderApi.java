package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.Order;
import com.ab.templateApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    private final OrderService orderService;

    @Autowired
    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order addOrder(@Valid @RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping
    public Order updateOrder(@Valid @RequestBody Order order) {
        return orderService.update(order);
    }
}
