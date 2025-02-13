package com.fiap.order.management.entity;

    import com.fiap.order.management.domain.StatusOrder;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.ArrayList;
    import java.util.List;

    @Entity(name = "orders") // "order" is a reserved keyword in MySQL
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private Long customerId;

        @Enumerated(EnumType.ORDINAL)
        private StatusOrder status;

        @OneToMany(cascade = CascadeType.ALL)
        private List<Item> items = new ArrayList<>();
    }
