package example.com.crud.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory(){
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
        }catch (Throwable e){
            System.err.println("Creation Session Factory is failed: " + e.getMessage());
        }

        return sessionFactory;
    }

    public static void closeSessionFactory(){
        getSessionFactory().close();
    }

    public static Session openSession(){
        session = getSessionFactory().openSession();
        return session;
    }



}
