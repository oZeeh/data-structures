import java.util.Locale;
import java.util.Scanner;

public class Beecrowd1437 {
    record point(char left, char right){}

    record directions(point north, point south, point east, point west){
        public point whereIAm(char direction) {
            return switch (direction) {
                case 'S' -> south;
                case 'L' -> east;
                case 'O' -> west;
                default -> north;
            };
        }
    }

    public static class Main {
        public static void main(String[] args) {

            final char west = 'O';
            final char east = 'L';
            final char north = 'N';
            final char south = 'S';

            var northPoint = new point(west, east);
            var southPoint = new point(east, west);
            var eastPoint = new point(north, south);
            var westPoint = new point(south, north);

            var directions = new directions(northPoint, southPoint, eastPoint, westPoint);

            char currentState = 'N';
            var  actualPoint = directions.whereIAm('N');

            Scanner scanner = new Scanner(System.in);
            int instructionNumbers = scanner.nextInt();
            while (instructionNumbers != 0) {

                String instructions = scanner.next();

                if (instructions.length() > instructionNumbers) {
                    return;
                }

                instructions = instructions.toUpperCase(Locale.ROOT);

                var instructionsArray = instructions.toCharArray();
                for (char instruction : instructionsArray) {
                    if (instruction == 'E') {
                        currentState = actualPoint.left();
                    } else {
                        currentState = actualPoint.right();
                    }
                    actualPoint = directions.whereIAm(currentState);
                }

                System.out.printf(String.valueOf(currentState) + '\n');
                instructionNumbers = scanner.nextInt();
                currentState = 'N';
                actualPoint = directions.whereIAm('N');
            }
        }
    }
}
