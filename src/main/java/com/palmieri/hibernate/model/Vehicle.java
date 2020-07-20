package com.palmieri.hibernate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="brand")
    private String brand;
    @Column(name="model")
    private String model;
    @Column(name="immdate")
    private Date immdate;
    @Column(name="plate")
    private String plate;
    @Column(name="type")
    private String type;

    @OneToMany (mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval=true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reservation> reservations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getImmdate() {
        return immdate;
    }

    public void setImmdate(Date immdate) {
        this.immdate = immdate;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations( Reservation reservation) {
        this.reservations.add(reservation) ;
    }
}
