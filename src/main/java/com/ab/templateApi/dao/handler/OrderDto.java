package com.ab.templateApi.dao.handler;

import com.ab.templateApi.dao.entity.Order;
import com.ab.templateApi.dao.entity.Room;
import com.ab.templateApi.dao.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long orderId;

    private User user;

    private Room room;

    private Timestamp reservationTime;

    private Integer amount;

    private Double price;

    private String orderStatus;

    private Timestamp submitTime;

    private Timestamp receiveTime;

    private String ticketType;

    private String msg;

    private String code;

    public OrderDto(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public static OrderDto toOrderDto(Order order, String msg, String code){
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .reservationTime(order.getReservationTime())
                .amount(order.getAmount())
                .price(order.getPrice())
                .orderStatus(order.getOrderStatus())
                .submitTime(order.getSubmitTime())
                .receiveTime(order.getReceiveTime())
                .ticketType(order.getTicketType())
                .msg(msg)
                .code(code)
                .build();
    }
}
