package example.com.crud.repository.impl;

import example.com.crud.model.Writer;
import example.com.crud.repository.WriterRepository;
import example.com.crud.utils.HibernateUtils;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBWriterRepositoryImpl implements WriterRepository {

    @Override
    public Writer getById(Long id) {
       try (Session session = HibernateUtils.openSession()){

           TypedQuery<Writer> writerTypedQuery = session.createQuery("SELECT w FROM Writer w LEFT JOIN FETCH w.posts where w.id = ?1", Writer.class);

           Writer writer = writerTypedQuery.setParameter(1, id).getSingleResult();

           return writer;
       }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            Writer writer = session.get(Writer.class, id);
            session.remove(writer);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed delete method " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Writer update(Writer writer) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            session.merge(writer);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed update method " + e.getMessage());
        }

        return writer;
    }

    @Override
    public List<Writer> getAll() {
        try(Session session = HibernateUtils.openSession()){

            List<Writer> writersList = session.createQuery("FROM Writer w LEFT JOIN FETCH w.posts", Writer.class).getResultList();

            return writersList;
        }
    }

    @Override
    public Writer save(Writer writer) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            session.persist(writer);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed save method " + e.getMessage());
        }

        return writer;
    }

}
