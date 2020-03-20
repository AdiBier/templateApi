package com.ab.templateApi.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId", nullable = false, unique = true, updatable = false)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "RoomId")
    private Room room;

    @Column(name = "ReservationTime")
    private Timestamp reservationTime;

    @Column(name = "Amount")
    private Integer amount;

    @Column(name = "Price")
    private Double price;

    @Column(name = "OrderStatus")
    private String orderStatus;

    @Column(name = "SubmitTime")
    private Timestamp submitTime;

    @Column(name = "ReceiveTime")
    private Timestamp receiveTime;

    @NotEmpty
    @Column(name = "Type", nullable = false)
    private String ticketType;

    public Order(Room room, User user, Timestamp reservationTime, int amount, double price, String orderStatus, Timestamp submitTime, Timestamp receiveTime, String ticketType) {
        this.room = room;
        this.user = user;
        this.reservationTime = reservationTime;
        this.amount = amount;
        this.price = price;
        this.orderStatus = orderStatus;
        this.submitTime = submitTime;
        this.receiveTime = receiveTime;
        this.ticketType = ticketType;
    }

    public Order(Timestamp reservationTime, int amount, double price, String orderStatus, Timestamp submitTime, Timestamp receiveTime, String ticketType) {
        this.reservationTime = reservationTime;
        this.amount = amount;
        this.price = price;
        this.orderStatus = orderStatus;
        this.submitTime = submitTime;
        this.receiveTime = receiveTime;
        this.ticketType = ticketType;
    }
}
