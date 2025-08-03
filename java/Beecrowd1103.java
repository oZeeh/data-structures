import java.util.Scanner;

public class Beecrowd1103 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int h1 = sc.nextInt();
            int m1 = sc.nextInt();
            int h2 = sc.nextInt();
            int m2 = sc.nextInt();

            if (h1 == 0 && m1 == 0 && h2 == 0 && m2 == 0)
                break;

            int start = h1 * 60 + m1;
            int end = h2 * 60 + m2;

            int diff = end - start;
            if (diff <= 0) {
                diff += 24 * 60;
            }

            System.out.println(diff);
        }

        sc.close();
    }
}