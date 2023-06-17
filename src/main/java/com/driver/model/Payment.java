package com.driver.model;

import javax.persistence.*;

@Table
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean paymentCompleted;
    private PaymentMode paymentMode;
    @OneToOne
    @JoinColumn
    private Reservation reservation;

    public void setId(int id) {
        this.id = id;
    }

    public void setPaymentCompleted(Boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public int getId() {
        return id;
    }

    public Boolean getPaymentCompleted() {
        return paymentCompleted;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Payment() {
    }
}