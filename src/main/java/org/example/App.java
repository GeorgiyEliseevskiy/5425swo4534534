package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try {

            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Person person = session.get(Person.class, 1);
            System.out.println("Eager");

            session.getTransaction().commit();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            person = (Person) session.merge(person);
            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();

            System.out.println(person.getItems());
        } finally {
            sessionFactory.close();
        }
    }
}
