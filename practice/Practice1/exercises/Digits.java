package Practice1.exercises;

/**
 * Created by Marva on 14.02.2016.
 */
public class Digits {
    public static void main(String[] args) {
        System.out.println(String.valueOf(071));
    }

    public static int sum(int number) {
        int sum=0;

        String text = String.valueOf(number);

        for (int i=0; i<text.length(); i++) {
            int digit = Character.digit(text.charAt(i), 10);
            sum+=digit;
        }
        return sum;
    }

    public static int sum2(int number) {
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;
            sum += digit;
            number /= 10;
        }
        return sum;
    }
}
