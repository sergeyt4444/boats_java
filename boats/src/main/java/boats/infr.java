/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author serge
 */
public class infr {
    
    private SessionFactory sessionFactory;

    public infr() {getSessionFactory();}

    public void getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().
                //        configure("/hibernate.cfg.xml");
                        configure();
                configuration.addAnnotatedClass(map_part.class);
                configuration.addAnnotatedClass(boat_table.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!  " + e);
            }
        }
    }

    public List<boat_table> getBoats() {
        return sessionFactory.openSession().createQuery("From boat_table").list();
    }

    public ArrayList<map_part> getMap() {
        List<map_part> list = sessionFactory.openSession().createQuery("From map_part").list();
        ArrayList<map_part> arr = new ArrayList<>(list.size());
        arr.addAll(list);
        return arr;
    }
    
    public void updateBoat(boat_table boat) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(boat);
        tx1.commit();
        session.close();
    }
    
    public void updateMap(map_part mp) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(mp);
        tx1.commit();
        session.close();
    }
    
    public void insertBoat(boat_table boat) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(boat);
        tx1.commit();
        session.close();
    }
    
    public void insertMap(map_part mp) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(mp);
        tx1.commit();
        session.close();
    }
    
    public void deleteBoat(boat_table boat) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(boat);
        tx1.commit();
        session.close();
    }
    
    public void clearMap() {
        sessionFactory.openSession().createQuery("DELETE From map_part").executeUpdate();
    }
}
