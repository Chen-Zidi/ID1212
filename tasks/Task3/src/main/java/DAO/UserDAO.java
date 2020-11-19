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

}
