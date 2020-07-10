package com.palmieri.hibernate.dao;

import com.palmieri.hibernate.conf.HibernateConf;
import com.palmieri.hibernate.model.User;
import com.palmieri.hibernate.model.Vehicle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class VehicleDAO {


    public static void saveVehicle(Vehicle vehicle){

        Transaction transaction = null;
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the vehicle object
            session.save(vehicle);
            /* commit transaction */
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }




    }

    public void deleteVehicle(int vehicleId) {

        Transaction trns = null;

        try(Session session = HibernateConf.getSessionFactory().openSession()) {
            trns = session.beginTransaction();
            Vehicle vehicle = (Vehicle) session.load(Vehicle.class, vehicleId);
            session.delete(vehicle);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Vehicle> getAllVehicles() {

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Transaction trns = null;
        //Session session = HibernateConf.getSessionFactory().openSession();
        try(Session session = HibernateConf.getSessionFactory().openSession())  {
            trns = session.beginTransaction();
            vehicles = session.createQuery("from Vehicle ").list();
            trns.commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }

            e.printStackTrace();
        }
        return vehicles;
    }

    public void updateVehicle(Vehicle vehicle) {

        Transaction trns = null;
        //Session session = HibernateConf.getSessionFactory().openSession();
        try(Session session = HibernateConf.getSessionFactory().openSession()){
            trns = session.beginTransaction();

            /*
            session.get(User.class, id).setUserName(userName);
            session.get(User.class, id).setPassword1(password);
            session.get(User.class, id).setEmail(email);
            session.get(User.class, id).setPhone(phone);
            session.get(User.class, id).setCity(city); */

            session.update(vehicle);
            trns.commit();
            session.flush();
            session.close();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }

    public Vehicle getVehicle(int id){

        Transaction transaction = null;
        Vehicle vehicle = null;
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            vehicle = session.get(Vehicle.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicle;




    }
}
