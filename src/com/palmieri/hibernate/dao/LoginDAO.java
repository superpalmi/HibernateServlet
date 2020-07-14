package com.palmieri.hibernate.dao;

import com.mysql.cj.Session;
import com.palmieri.hibernate.conf.HibernateConf;
import com.palmieri.hibernate.model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

import org.hibernate.Transaction;

public class LoginDAO {

    public boolean authenticateUser(String username, String password) {
        User user = getUserByUserName(username);
        if((user!=null) && (user.getUserName().equals(username)) && (user.getPassword1().equals(password))){
            return true;
        }else{
            return false;
        }
    }

    public User getUserByUserName(String userName) {
        Transaction transaction =null;

        User user = null;
        try (org.hibernate.Session session = HibernateConf.getSessionFactory().openSession()){
            transaction= session.beginTransaction();
            user = session.createQuery("from User where userName =:userName", User.class).setParameter("userName", userName).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getListOfUsers(){
        List<User> users = new ArrayList<User>();
        org.hibernate.Session session = HibernateConf.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            users = session.createQuery("from User").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }
}
