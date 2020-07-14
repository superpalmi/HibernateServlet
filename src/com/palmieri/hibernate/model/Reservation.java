package com.palmieri.hibernate.model;
import javax.persistence.*;

@Entity
@Table (name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name="dataInizio", nullable = false)
    private String dataInizio;
    @Column(name="dataFine", nullable = false)
    private String dataFine;
    @OneToOne
    @JoinColumn(name="UserId")
    private User user;
    @OneToOne
    @JoinColumn(name="VehicleId")
    private Vehicle vehicle;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user= userId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicleId) {
        this.vehicle = vehicleId;
    }




}
