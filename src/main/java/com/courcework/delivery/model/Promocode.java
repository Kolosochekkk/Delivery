package com.courcework.delivery.model;

import javax.persistence.*;

@Entity
@Table(name="discount")
public class Promocode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code;
    private double discount;

    public Promocode(){}

    public Promocode(Long id, int code, double discount) {
        this.id = id;
        this.code = code;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

