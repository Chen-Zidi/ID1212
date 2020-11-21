package DAO;
import bean.Question;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<Question>();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("select q from Question q");

        for(int i = 0; i<query.getResultList().size();i++){
            questionList.add((Question) query.getResultList().get(i));
        }

        em.close();
        factory.close();

        return questionList;

    }

    public void createQuestion(List<Question> newQuestionList){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");
        EntityManager em = factory.createEntityManager();

        for(Question q : newQuestionList){
            em.getTransaction().begin();
            em.persist(q);
            em.getTransaction().commit();

        }
        em.close();
        factory.close();

    }
    public void createQuestion(Question newQuestion){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("project");
        EntityManager em = factory.createEntityManager();


            em.getTransaction().begin();
            em.persist(newQuestion);
            em.getTransaction().commit();


        em.close();
        factory.close();

    }
}
