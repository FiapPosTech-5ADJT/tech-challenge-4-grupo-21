package br.com.fiap.logistic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_person")
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String vehiclePlate;

    @Column(nullable = false)
    private boolean available = true;
}
