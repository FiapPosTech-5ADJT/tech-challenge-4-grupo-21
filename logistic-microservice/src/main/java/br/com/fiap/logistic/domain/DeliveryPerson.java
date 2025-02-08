package br.com.fiap.logistic.domain;

public class DeliveryPerson {

    private Long id;
    private String name;
    private String vehiclePlate;
    private boolean available;

    public DeliveryPerson(Long id, String name, String vehiclePlate) {
        this.id = id;
        this.name = name;
        this.vehiclePlate = vehiclePlate;
        this.available = true;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return this.available;
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
}
