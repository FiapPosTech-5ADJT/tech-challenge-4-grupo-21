package br.com.fiap.productcatalog.domain.entity;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private BigDecimal stock;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price, Category category, BigDecimal stock) {
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
        setCategory(category);
        setStock(stock);
    }

    public Product(String name, String description, BigDecimal price, Category category, BigDecimal stock) {
        setName(name);
        setDescription(description);
        setPrice(price);
        setCategory(category);
        setStock(stock);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        this.category = category;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock cannot be null");
        }

        if (stock.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stock = stock;
    }

    public void addStock(BigDecimal quantity) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (quantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.stock = this.stock.add(quantity);
    }

    public void removeStock(BigDecimal quantity) {
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (quantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        if (this.stock.compareTo(quantity) < 0) {
            throw new IllegalArgumentException("Quantity cannot be greater than stock");
        }
        this.stock = this.stock.subtract(quantity);
    }
}