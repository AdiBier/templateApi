package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.Order;
import com.ab.templateApi.dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order newOrder) {
        Order order = new Order(newOrder.getRoom(), newOrder.getUser(), newOrder.getReservationTime(), newOrder.getAmount(), newOrder.getPrice(),
                newOrder.getOrderStatus(), newOrder.getSubmitTime(), newOrder.getReceiveTime(), newOrder.getTicketType());
        return orderRepository.save(order);
    }

    public Order update(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @EventListener(ApplicationEvent.class)
    public void fillDb(){
//        save(new Order());
//        save(new Order());
//        save(new Order());
    }
}
