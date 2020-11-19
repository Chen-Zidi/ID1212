import bean.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class JpaTest {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");
        EntityManager em = factory.createEntityManager();
        // create new
        em.getTransaction().begin();
        User user = new User();
        user.setUsername("amao");
        user.setPassword("321321");
        em.persist(user);
        em.getTransaction().commit();
        // read the existing entries and write to console
        Query jpql = em.createQuery("select u from User u");
        List<User> userList = jpql.getResultList();
        for (User u: userList) {
            System.out.println(u);
        }
        System.out.println("Size: " + userList.size());

        em.close();
    }

}
