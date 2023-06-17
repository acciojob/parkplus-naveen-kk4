package com.driver.model;

import javax.persistence.*;

@Table
@Entity

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numberOfHours;

    @ManyToOne
    @JoinColumn
    private Spot spot;
    @ManyToOne
    @JoinColumn
    private User user;
    @OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL)
    private Payment payment;

    public void setId(int id) {
        this.id = id;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public Spot getSpot() {
        return spot;
    }

    public User getUser() {
        return user;
    }

    public Payment getPayment() {
        return payment;
    }

    public Reservation() {
    }
}
