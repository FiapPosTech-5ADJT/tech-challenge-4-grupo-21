package com.fiap.order.management.dto;

public enum TypeEnum {
    LIGHT(1),
    NORMAL(2),
    HEAVY(3);

    private int id;

    TypeEnum(int id) {
        this.id = id;
    }
}
