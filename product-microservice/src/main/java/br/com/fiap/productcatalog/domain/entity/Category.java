package br.com.fiap.productcatalog.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private Long id;
    private String name;
    private List<Product> products = new ArrayList<>();

    public Category(Long id, String name, List<Product> products) {
        setId(id);
        setName(name);
        setProducts(products);
    }

    public Category(String name) {
        setName(name);
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
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products cannot be null or empty");
        }
        this.products = products;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        this.products.add(product);
    }
}