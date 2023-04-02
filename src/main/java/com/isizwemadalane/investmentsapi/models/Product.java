package com.isizwemadalane.investmentsapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "CURRENT_BALANCE"))
    private Amount currentBalance = Amount.zero();

    protected Product() { }
    public Product(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
