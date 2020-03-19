package com.ab.templateApi.dao.entity;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }
}
