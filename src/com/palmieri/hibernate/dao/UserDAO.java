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

        try(Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the vehicle object
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

    public void deleteUser(int userid) {
        Transaction trns = null;

        try(Session session = HibernateConf.getSessionFactory().openSession()) {
            trns = session.beginTransaction();
            User user = (User) session.load(User.class, userid);
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        }
    }



    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Transaction trns = null;
        //Session session = HibernateConf.getSessionFactory().openSession();
        try(Session session = HibernateConf.getSessionFactory().openSession())  {
            trns = session.beginTransaction();
            users = session.createQuery("from User").list();
            trns.commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }

            e.printStackTrace();
        }
        return users;
    }

    public void updateUser(User user){

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

            session.update(user);
            trns.commit();
            session.flush();
            session.close();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }

    public User getUser(int id) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateConf.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.get(User.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }




}