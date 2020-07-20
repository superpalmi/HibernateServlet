package com.palmieri.hibernate.dao;

import com.palmieri.hibernate.conf.HibernateConf;
import com.palmieri.hibernate.model.Reservation;
import com.palmieri.hibernate.model.User;
import com.palmieri.hibernate.model.Vehicle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {


    public void saveReservation(Reservation reservation) {
        Transaction transaction = null;

        try(Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the vehicle object
            session.save(reservation);

            // commit transaction
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }

    public Object getAllReservations() {
        List<Reservation> reservations = new ArrayList<Reservation>();
        Transaction trns = null;
        //Session session = HibernateConf.getSessionFactory().openSession();
        try(Session session = HibernateConf.getSessionFactory().openSession())  {
            trns = session.beginTransaction();
            reservations = session.createQuery("from Reservation ").list();
            trns.commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }

            e.printStackTrace();
        }
        return reservations;
    }

    public void deleteReservation(int reservationId) {
        Transaction trns = null;

        try(Session session = HibernateConf.getSessionFactory().openSession()) {
            trns = session.beginTransaction();

            Reservation reservation = (Reservation) session.load(Reservation.class, reservationId);
            User user = (User) session.load(User.class, reservation.getUser().getId());
            Vehicle vehicle=(Vehicle) session.load(Vehicle.class, reservation.getVehicle().getId());
            user.getReservations().remove(reservation);
            vehicle.getReservations().remove(reservation);


            session.save(user);
            session.save(vehicle);

            session.delete(reservation);
            trns.commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        }


    }

    public User getUser(int userId) {
        User user = null;
        //Reservation res = getReservation(reservationId);
        Transaction trns = null;
        try(Session session = HibernateConf.getSessionFactory().openSession())  {
            trns = session.beginTransaction();

            //user=res.getUser();
            user = (User) session.createQuery("select User  from User join Reservation res on res.user.id=User.id WHERE "+ userId+"=res.user.id");
            trns.commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }

            e.printStackTrace();
        }
        return user;

    }

    public Vehicle getVehicle(int vehicleId) {
       Vehicle vehicle = null;
       //Reservation res = getReservation(reservationId);
        Transaction trns = null;
        try(Session session = HibernateConf.getSessionFactory().openSession())  {
            trns = session.beginTransaction();
            ///vehicle = res.getVehicle();
            vehicle = (Vehicle) session.createQuery(" from Vehicle join Reservation  on Reservation.vehicle.id=Vehicle.id where "+ vehicleId+"=Reservation .vehicle.id");
            trns.commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }

            e.printStackTrace();
        }
        return vehicle;

    }

    public void updateReservation(Reservation reservation) {
        Transaction trns = null;
        //Session session = HibernateConf.getSessionFactory().openSession();
        try(Session session = HibernateConf.getSessionFactory().openSession()){
            trns = session.beginTransaction();



            session.update(reservation);
            trns.commit();


        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }

    public Reservation getReservation(int i) {
        Transaction transaction = null;
        Reservation res = null;
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            res = session.get(Reservation.class, i);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return res;

    }
}
