package example.com.crud.repository.impl;

import example.com.crud.model.Label;
import example.com.crud.repository.LabelRepository;
import example.com.crud.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBLabelRepositoryImpl implements LabelRepository {

    @Override
    public Label getById(Long id) {
        try (Session session = HibernateUtils.openSession()) {

            Label label = session.get(Label.class, id);

            return label;
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();

            Label label = session.get(Label.class, id);
            session.remove(label);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed delete method " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Label update(Label label) {
        Transaction transaction = null;

        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();

            session.merge(label);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed update method " + e.getMessage());
        }

        return label;
    }

    @Override
    public List<Label> getAll() {
        try (Session session = HibernateUtils.openSession()) {
            List<Label> labelsList = session.createQuery("FROM Label", Label.class).getResultList();

            return labelsList;
        }
    }

    @Override
    public Label save(Label label) {

        Transaction transaction = null;
        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();

            session.persist(label);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed save method " + e.getMessage());
        }

        return label;
    }
}
