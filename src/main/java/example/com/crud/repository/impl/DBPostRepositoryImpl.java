package example.com.crud.repository.impl;

import example.com.crud.model.Post;

import example.com.crud.repository.PostRepository;
import example.com.crud.utils.HibernateUtils;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBPostRepositoryImpl implements PostRepository {

    @Override
    public Post getById(Long id) {
        try (Session session = HibernateUtils.openSession()) {

            TypedQuery<Post> postTypedQuery = session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.writer JOIN FETCH p.labels where p.id = ?1", Post.class);

            Post post = postTypedQuery.setParameter(1, id).getSingleResult();

            return post;
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();

            Post post = session.get(Post.class, id);
            session.remove(post);

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
    public Post update(Post post) {
        Transaction transaction = null;

        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();

            session.merge(post);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed update method " + e.getMessage());
        }

        return post;
    }

    @Override
    public List<Post> getAll() {
        try (Session session = HibernateUtils.openSession()) {

            List<Post> postsList = session.createQuery("FROM Post p LEFT JOIN FETCH p.labels join fetch p.writer", Post.class).getResultList();

            return postsList;
        }
    }

    @Override
    public Post save(Post post) {
        Transaction transaction = null;

        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();

            session.persist(post);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed save method " + e.getMessage());
        }

        return post;
    }

}
