package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.Order;
import com.ab.templateApi.dao.entity.Room;
import com.ab.templateApi.dao.entity.WorkHour;
import com.ab.templateApi.dao.handler.OrderDto;
import com.ab.templateApi.dao.handler.WorkHourDto;
import com.ab.templateApi.response.ServerResponse;
import com.ab.templateApi.response.ServerResponseConstants;
import com.ab.templateApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    private final OrderService orderService;

    @Autowired
    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public Iterable<OrderDto> getAllWorkHours() {
        Iterable<Order> orderIterable = orderService.findAll();
        if (orderIterable.iterator().hasNext()) {
            return StreamSupport.stream(orderIterable.spliterator(), false)
                    .map(orders -> {
                        return new OrderDto(orders.getOrderId(), orders.getUser(), orders.getRoom(), orders.getReservationTime(), orders.getAmount(), orders.getPrice(), orders.getOrderStatus(), orders.getSubmitTime(), orders.getReceiveTime(), orders.getTicketType(),
                                ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
                    }).collect(toList());
        }
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderDtoList.add(new OrderDto(null, null,null, null, null, null, null, null, null, null, ServerResponseConstants.TAE1005_MSG, ServerResponseConstants.TAE1005_CODE));
        return orderDtoList;
    }

    @PostMapping
    public Order addOrder(@Valid @RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping
    public Order updateOrder(@Valid @RequestBody Order order) {
        return orderService.update(order);
    }

    @DeleteMapping
    public ServerResponse deleteOrderById(@RequestParam Long id) {
        if (orderService.findById(id).isPresent()){
            orderService.delete(id);
            return new ServerResponse(ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
        }
        return new ServerResponse(ServerResponseConstants.TAE1005_MSG, ServerResponseConstants.TAE1005_CODE);
    }
}
