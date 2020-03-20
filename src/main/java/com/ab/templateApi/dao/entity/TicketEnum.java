package com.ab.templateApi.dao.entity;

import lombok.Getter;

@Getter
public enum TicketEnum {
    ADULT("ADULT", 25.0),
    STUDENT("STUDENT", 18.0),
    CHILD("CHILD", 12.5);

    private String name;

    private Double price;

    TicketEnum(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
