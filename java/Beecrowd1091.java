import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Beecrowd1091 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, String> directions = new HashMap<>();
        directions.put("++", "NE");
        directions.put("-+", "NO");
        directions.put("--", "SO");
        directions.put("+-", "SE");

        while (true) {
            int K = sc.nextInt();
            if (K == 0) break;

            int N = sc.nextInt();
            int M = sc.nextInt();

            for (int i = 0; i < K; i++) {
                int X = sc.nextInt();
                int Y = sc.nextInt();

                if (X == N || Y == M) {
                    System.out.println("divisa");
                } else {
                    String key = (X > N ? "+" : "-") + (Y > M ? "+" : "-");
                    System.out.println(directions.get(key));
                }
            }
        }

        sc.close();

    }
}
