package com.palmieri.hibernate.dao;

import com.palmieri.hibernate.conf.HibernateConf;
import com.palmieri.hibernate.model.Vehicle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class VehicleDAO {


    public static void saveVehicle(Vehicle vehicle){

        Transaction transaction = null;
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
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
}
