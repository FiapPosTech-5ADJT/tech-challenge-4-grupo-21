package br.com.fiap.logistic.domain;

import java.util.ArrayList;
import java.util.List;

public class DeliveryPerson {

    private static final int MAX_ORDERS = 5; // Limite de pedidos por entregador

    private Long id;
    private String name;
    private String vehiclePlate;
    private boolean available;
    private List<Order> orders;

    public DeliveryPerson(String name, String vehiclePlate) {
        this.name = name;
        this.vehiclePlate = vehiclePlate;
        this.available = true;
        this.orders = new ArrayList<>();
    }

    public DeliveryPerson(Long id, String name, String vehiclePlate, boolean available, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.vehiclePlate = vehiclePlate;
        this.available = available;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void assignOrder(Order order) {
        if (orders.size() >= MAX_ORDERS) {
            throw new IllegalStateException("O entregador já atingiu o limite máximo de 5 pedidos.");
        }
        if (!orders.contains(order)) {
            this.orders.add(order);
            if (orders.size() == MAX_ORDERS) {
                this.available = false;
            }
        }
    }

    public void completeOrder(Order order) {
        this.orders.remove(order);
        if (this.orders.isEmpty()) {
            this.available = true; // Se não houver mais pedidos, fica disponível
        }
    }
}
