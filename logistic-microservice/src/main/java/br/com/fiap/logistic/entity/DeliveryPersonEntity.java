package br.com.fiap.logistic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_person")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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

    @OneToMany(mappedBy = "deliveryPerson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<>();
}
