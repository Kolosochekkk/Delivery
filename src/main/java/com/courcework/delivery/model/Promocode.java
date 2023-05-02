package com.courcework.delivery.model;

import javax.persistence.*;

@Entity
@Table(name="discount")
public class Promocode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code;
    private double summ;

    public Promocode(){}

    public Promocode(Long id, int code, double summ) {
        this.id = id;
        this.code = code;
        this.summ = summ;
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

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }
}

