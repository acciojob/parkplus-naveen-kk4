package com.driver.services.impl;

import com.driver.model.*;
import com.driver.model.Reservation;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;

    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        Optional<User> userOptional = userRepository3.findById(userId);
        if (userOptional.isEmpty()) throw new Exception("Cannot make reservation");
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository3.findById(parkingLotId);
        if (parkingLotOptional.isEmpty()) throw new Exception("Cannot make reservation");
        User user = userOptional.get();
        ParkingLot parkingLot = parkingLotOptional.get();
        List<Spot> spotList = parkingLot.getSpotList();
        int minCost = Integer.MAX_VALUE;
        Spot bookedSpot = null;

        for (Spot spot : spotList) {
            if (spot.getSpotType() == SpotType.TWO_WHEELER) {
                if (numberOfWheels <= 2 && spot.getOccupied() == Boolean.FALSE && spot.getPricePerHour() < minCost) {
                    minCost = spot.getPricePerHour();
                    bookedSpot = spot;
                }
            }
            if (spot.getSpotType() == SpotType.FOUR_WHEELER) {
                if (numberOfWheels <= 4 && spot.getOccupied() == Boolean.FALSE && spot.getPricePerHour() < minCost) {
                    minCost = spot.getPricePerHour();
                    bookedSpot = spot;
                }
            }
            if (spot.getSpotType() == SpotType.OTHERS) {
                if (spot.getOccupied() == Boolean.FALSE && spot.getPricePerHour() < minCost) {
                    minCost = spot.getPricePerHour();
                    bookedSpot = spot;
                }

            }

        }
        if (Objects.isNull(bookedSpot)) throw new Exception("Cannot make reservation");
        bookedSpot.setOccupied(Boolean.TRUE);
        Reservation reservation = new Reservation();
        reservation.setNumberOfHours(timeInHours);
        reservation.setSpot(bookedSpot);
        reservation.setUser(user);
        reservation = reservationRepository3.save(reservation);
        bookedSpot.getReservationList().add(reservation);
        user.getReservationList().add(reservation);
        userRepository3.save(user);
        spotRepository3.save(bookedSpot);
        return reservation;


    }
}