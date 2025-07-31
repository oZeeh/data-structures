import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Beecrowd1192 {
    public static class Main {
        private static final Pattern PATTERN = Pattern.compile("(\\d+)([A-Z]|[a-z])(\\d+)");

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int T = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < T; i++) {
                String line = sc.nextLine();

                Matcher matcher = PATTERN.matcher(line);
                int result = 0;

                while (matcher.find()) {
                    int n1 = Integer.parseInt(matcher.group(1));
                    String operator = matcher.group(2);
                    int n2 = Integer.parseInt(matcher.group(3));

                    if (n1 == n2) {
                        result = n1 * n2;
                        break;
                    }

                    if (Character.isUpperCase(operator.charAt(0))) {
                        result = n2 - n1;
                        break;
                    }

                    result = n1 + n2;
                }

                System.out.println(result);
            }
        }
    }
}
