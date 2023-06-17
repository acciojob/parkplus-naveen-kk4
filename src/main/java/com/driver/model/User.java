package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="users")
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public User() {
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();
}
