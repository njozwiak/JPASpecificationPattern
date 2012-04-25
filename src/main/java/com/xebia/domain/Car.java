package com.xebia.domain;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "CAR")
@Table(name = "CAR")
@Access(AccessType.FIELD)
public class Car {

    @Id
    @Column(nullable = false)
    private Integer id;

    @Column
    private String constructor;

    @Column
    private String model;

    @Column
    private String fuel;

    @Column
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date buildDate;

    public Car() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }
}