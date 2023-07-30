package net.erasmatov.crudapp.repository.hibernate;

import net.erasmatov.crudapp.model.Developer;
import net.erasmatov.crudapp.model.Status;
import net.erasmatov.crudapp.repository.DeveloperRepository;
import net.erasmatov.crudapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateDeveloperRepositoryImpl implements DeveloperRepository {
    private Transaction transaction = null;

    @Override
    public List<Developer> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery(
                    "FROM Developer d LEFT JOIN FETCH d.skills", Developer.class).list();
        }
    }

    @Override
    public Developer getById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Developer> query = session.createQuery(
                    "FROM Developer d LEFT JOIN FETCH d.skills WHERE d.id = :id", Developer.class);
            query.setParameter(id, "id");
            return query.uniqueResult();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, id);
            developer.setStatus(Status.DELETED);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Developer save(Developer developer) {
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(developer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(developer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return developer;
    }

}
