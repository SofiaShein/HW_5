package jpa1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ApartmentDAO {
    private final SessionFactory sessionFactory;

    public ApartmentDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void save(Apartment apartment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(apartment);
            tx.commit();
        }
    }

    public List<Apartment> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Apartment", Apartment.class).list();
        }
    }

    public void update(Apartment apartment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(apartment);
            tx.commit();
        }
    }

    public void delete(Apartment apartment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(apartment);
            tx.commit();
        }
    }

    public List<Apartment> findByPriceAndRooms(int price, String operator, int rooms) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Apartment where price " + operator + " :price and rooms = :rooms";
            return session.createQuery(hql, Apartment.class)
                    .setParameter("price", price)
                    .setParameter("rooms", rooms)
                    .list();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
