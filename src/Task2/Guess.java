package Task2;

import java.util.Random;

public class Guess {


    private final int number;
    private int counter;

    public Guess(){
        //this.number = (int)(Math.random()*100);
        this.number = new Random().nextInt(99) + 1;
        this.counter = 0 ;
    }

    public Guess(int number, int counter) {
        this.number = number;
        this.counter = counter;
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

    public int getNumber() {
        return number;
    }

    public int getCounter() {
        return counter;
    }

    public void count() {
        counter++;
    }
}
