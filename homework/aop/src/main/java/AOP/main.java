package main.java.AOP;

import org.jetbrains.annotations.Contract;

/**
 * Created by marva on 20.05.16.
 */
public class main {

    public static void main(String[] args) {

        try {
            printPrimes(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @LogTime
    public static int printPrimes(int range) throws InterruptedException {
        Thread.sleep(10000);

        return range*range;
    }
}
