package com.palmieri.hibernate.conf;
import java.util.Properties;

import com.palmieri.hibernate.model.Reservation;
import com.palmieri.hibernate.model.User;
import com.palmieri.hibernate.model.Vehicle;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateConf {

    private static SessionFactory sessionFactory;

    //private static final SessionFactory sessionFactory = buildSessionFactory();


    public static SessionFactory getSessionFactory() {

            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration();

                    // Hibernate settings equivalent to hibernate.cfg.xml's properties
                    Properties settings = new Properties();
                    settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/jwt?useLegacyDatetimeCode=false&amp&serverTimezone=Europe/Amsterdam&amp&useSSL=false");
                    settings.put(Environment.USER, "user");
                    settings.put(Environment.PASS, "si2001");
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                    settings.put(Environment.SHOW_SQL, "true");

                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                    settings.put(Environment.HBM2DDL_AUTO, "update");

                    configuration.setProperties(settings);
                    configuration.addAnnotatedClass(User.class);
                    configuration.addAnnotatedClass(Vehicle.class);
                    configuration.addAnnotatedClass(Reservation.class);


                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                    System.out.println("Hibernate Java Config serviceRegistry created");
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                    return sessionFactory;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sessionFactory;

    }
    /*
    */
}
