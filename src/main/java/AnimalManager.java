import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AnimalManager {
    public static void addAnimal(Animal a) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(a);
        session.getTransaction().commit();
        session.close();
    }

    public static ObservableList<Animal> readAnimals() {
        List<Animal> animals;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        animals = session.createSQLQuery("SELECT * FROM animals").addEntity(Animal.class).list();
        session.close();
        ObservableList<Animal> arr = FXCollections.observableArrayList();
        arr.addAll(animals);
        return arr;
    }

    public static void deleteAnimal(Animal a) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(a);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
    }

    public static void updateAnimal(Animal a) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(a);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
    }
}

