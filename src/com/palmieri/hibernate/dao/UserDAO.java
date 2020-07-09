package com.palmieri.hibernate.dao;
import com.palmieri.hibernate.conf.HibernateConf;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.*;
import org.hibernate.cfg.Configuration;

import com.palmieri.hibernate.model.User;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAO {

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    public List<User> readUsers(){

        List<User> users = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            //transaction = session.beginTransaction();

            users = session.createQuery("from User", User.class).list();



            // commit transaction
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return users;


    }

}