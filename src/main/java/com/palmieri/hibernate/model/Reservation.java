package com.palmieri.hibernate.model;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table (name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name="dataInizio", nullable = false)

    @Temporal(TemporalType.DATE)
    private Date dataInizio;
    @Column(name="dataFine", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFine;

    @ManyToOne()
    @JoinColumn(name= "users")
    private User user;
    @ManyToOne()
    @JoinColumn(name= "vehicles")
    private Vehicle vehicle;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
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


    @ManyToOne(optional = false)
    private User users;

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @ManyToOne(optional = false)
    private Vehicle vehicles;

    public Vehicle getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle vehicles) {
        this.vehicles = vehicles;
    }
}
