package Task2;

public class Guess {


    int number;
    int counter;

    public Guess(){
        number = (int) (Math.random() * 100 + 1);
        counter = 0;
    }

    public String compare(int i){
        counter ++;
        if(i > number){
            return "higher";
        }else if(i == number){
            return "equal";
        }
        return "lower";

    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
