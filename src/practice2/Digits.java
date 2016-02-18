package practice2;

/**
 * Created by Marva on 14.02.2016.
 */
public class Digits {
    public static int product(int number) {
        int sum=1;

        String text = String.valueOf(number);

        for (int i=0; i<text.length(); i++) {
            int digit = Character.digit(text.charAt(i),10);
            sum*=digit;
        }
        return Math.abs(sum);
    }
}
