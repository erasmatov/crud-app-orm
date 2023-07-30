package net.erasmatov.crudapp.repository.hibernate;

import net.erasmatov.crudapp.model.Skill;
import net.erasmatov.crudapp.model.Status;
import net.erasmatov.crudapp.repository.SkillRepository;
import net.erasmatov.crudapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateSkillRepositoryImpl implements SkillRepository {
    private Transaction transaction = null;

    @Override
    public List<Skill> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from Skill", Skill.class).list();
        }
    }

    @Override
    public Skill getById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Skill.class, id);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Skill skill = session.get(Skill.class, id);
            skill.setStatus(Status.DELETED);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Skill save(Skill skill) {
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(skill);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.merge(skill);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return skill;
    }

}
