package com.palmieri.hibernate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="username", nullable = false)
    private String userName;

    @Column(name="password", nullable = false)
    private String password1;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="phone", nullable = false)
    private String phone;
    @Column(name="city", nullable = false)
    private String city;



    @Column(name="role", nullable = true)
    private String role;




    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reservation> reservations;







    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations( Reservation reservation) {
        this.reservations.add(reservation) ;
    }



}
