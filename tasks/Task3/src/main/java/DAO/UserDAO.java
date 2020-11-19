package DAO;

import bean.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDAO {

    public User login(String username, String password){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");
        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery("select u from User u where u.username=:Username and u.password=:Password");
        query.setParameter("Username",username);
        query.setParameter("Password",password);

        User user = (User) query.getSingleResult();

        em.close();
        factory.close();

        return user;
    }

    public User register(String username, String password, String email){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");
        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery("select u from User u where u.username=:Username");
        query.setParameter("Username",username);
        if (query.getResultList().size() != 0) {
            return null;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();
        factory.close();

        return user;
    }

}
