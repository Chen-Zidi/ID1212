import bean.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class JpaTest {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");
        EntityManager em = factory.createEntityManager();

        // create a new user
//        em.getTransaction().begin();
//        User user = new User();
//        user.setUsername("amao");
//        user.setPassword("321321");
//        em.persist(user);
//        em.getTransaction().commit();
//        // read the existing entries and write to console
//        Query jpql = em.createQuery("select u from User u");
//        List<User> userList = jpql.getResultList();
//        for (User u: userList) {
//            System.out.println(u);
//        }
//        System.out.println("Size: " + userList.size());


        // create a new user
        em.getTransaction().begin();
        Question q = new Question();
        q.setQuestion("What is your favourite food?");
        q.setChoice0("Vietnamese");
        q.setChoice1("Chinese food");
        q.setChoice2("Italian food");
        q.setChoice3("French food");
        q.setChoice4("other");
        em.persist(q);
        em.getTransaction().commit();
        //read the existing entries and write to console
        Query jpql = em.createQuery("select q from Question q");
        List<Question> questionList = jpql.getResultList();
        for (Question question: questionList) {
            System.out.println(question);
        }
        System.out.println("Size: " + questionList.size());


        em.close();
    }

}
