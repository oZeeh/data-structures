import java.util.*;
import java.util.concurrent.*;

public class Beecrowd1383 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());

        List<int[][]> sudokuBoards = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int[][] board = new int[9][9];
            for (int row = 0; row < 9; row++) {
                String[] parts = scanner.nextLine().trim().split("\\s+");
                for (int col = 0; col < 9; col++) {
                    board[row][col] = Integer.parseInt(parts[col]);
                }
            }
            sudokuBoards.add(board);
        }

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Boolean>> futures = new ArrayList<>();

        for (int[][] board : sudokuBoards) {
            futures.add(executor.submit(() -> validateSudoku(board)));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        for (int i = 0; i < futures.size(); i++) {
            boolean valid;
            try {
                valid = futures.get(i).get();
            } catch (Exception e) {
                valid = false;
            }
            System.out.println("Instancia " + (i + 1));
            System.out.println(valid ? "SIM" : "NAO");
            System.out.println();
        }
    }

    private static boolean validateSudoku ( int[][] board) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        Future<Boolean> rows = exec.submit(() -> checkRows(board));
        Future<Boolean> cols = exec.submit(() -> checkColumns(board));
        Future<Boolean> blocks = exec.submit(() -> checkBlocks(board));

        boolean result = rows.get() && cols.get() && blocks.get();
        exec.shutdown();
        return result;
    }

    private static boolean checkRows ( int[][] board){
        for (int i = 0; i < 9; i++) {
            boolean[] seen = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int num = board[i][j];
                if (num < 1 || num > 9 || seen[num]) return false;
                seen[num] = true;
            }
        }
        return true;
    }

    private static boolean checkColumns ( int[][] board){
        for (int j = 0; j < 9; j++) {
            boolean[] seen = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int num = board[i][j];
                if (num < 1 || num > 9 || seen[num]) return false;
                seen[num] = true;
            }
        }
        return true;
    }

    private static boolean checkBlocks ( int[][] board){
        for (int block = 0; block < 9; block++) {
            boolean[] seen = new boolean[10];
            int rowOffset = (block / 3) * 3;
            int colOffset = (block % 3) * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int num = board[rowOffset + i][colOffset + j];
                    if (num < 1 || num > 9 || seen[num]) return false;
                    seen[num] = true;
                }
            }
        }
        return true;
    }
}
