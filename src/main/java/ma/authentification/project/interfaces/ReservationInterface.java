package ma.authentification.project.interfaces;

import ma.authentification.project.entities.Reservation;
import ma.authentification.project.exceptions.ReservationException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationInterface {

    public List<Reservation> findAllReservations() throws ReservationException;

    public Reservation findReservationById(Integer id) throws ReservationException;

    public List<Reservation> findReservationsByDateBetween(LocalDate debutDate,LocalDate finalDate) throws ReservationException;

    public List<Reservation> findReservationsByDateAndTimeBetween(LocalDate date, LocalTime beginTime, LocalTime endTime) throws ReservationException;

    public List<Reservation> findReservationsByDuree(Integer duree) throws  ReservationException;

    public Double totalPrice(Reservation reservation) throws ReservationException;

    public void addServiceToReservation(Integer idSer,Integer idRes) throws Exception;

    public void updateFacture(Reservation reservation) throws  ReservationException;

    public void removeServiceFromReservation(Integer idSer,Integer idRes) throws Exception,ReservationException;

    public Reservation saveReservation(Reservation reservation) throws ReservationException,Exception;

    public Reservation updateReservation(Reservation reservation) throws Exception,ReservationException;

    public void deleteReservationById(Integer id) throws ReservationException;






}

