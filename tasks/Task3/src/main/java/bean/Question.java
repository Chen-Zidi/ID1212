package bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String choice0;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    public Question(){ }

    public Question(String question, String choice0, String choice1, String choice2, String choice3, String choice4) {
        this.question = question;
        this.choice0 = choice0;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice0() {
        return choice0;
    }

    public void setChoice0(String choice0) {
        this.choice0 = choice0;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }
}
