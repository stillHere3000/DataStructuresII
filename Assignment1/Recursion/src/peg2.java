import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class peg2 {

    private final int SIZE = 7;
    private final int POINTS = 33; // Change this value for different variants
    private final int[][] points = new int[POINTS][2]; // Stores coordinates of valid locations
    private final int FIRST_I = 3;
    private final int FIRST_J = 3;
    private final int LAST_I = 3;
    private final int LAST_J = 3;

    private
        
        int[][] tempPoints = {// Initialize the points array based on the variant
        // For the English variant
                {0, 2}, {0, 3}, {0, 4},
                {1, 2}, {1, 3}, {1, 4},
                {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6},
                {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {3, 6},
                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}, {4, 6},
                {5, 2}, {5, 3}, {5, 4},
                {6, 2}, {6, 3}, {6, 4}
        };
        
    

   

    public static void main(String[] args) {
        peg2 puzzle = new peg2();
        boolean[][] array = new boolean[puzzle.SIZE][puzzle.SIZE];
        System.arraycopy(puzzle.tempPoints, 0, puzzle.points, 0, puzzle.POINTS);
        
        puzzle.init(array);
        puzzle.printBoard(array);
        if (puzzle.puzzle2(array, puzzle.POINTS - 1, true)) {
            puzzle.printBoard(array);
        } else {
            System.out.println("No solution found.");
        }
    }

    public boolean puzzle2(boolean[][] array, int remaining, boolean reset) {
        Set<Long> visited = new HashSet<>();
        Stack<Jump> solutionTrace = new Stack<>();

        int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        if (reset) {
            visited.clear();
        }

        if (remaining == 1) {
            return solutionFound(array);
        }

        long id = idBoard(array);
        if (visited.contains(id)) {
            return false;
        }

        for (int pt = 0; pt < POINTS; pt++) {
            int i = points[pt][0];
            int j = points[pt][1];

            if (!array[i][j]) {
                for (int[] move : moves) {
                    int p = i + move[0];
                    int q = j + move[1];

                    int m = p + move[0];
                    int n = q + move[1];

                    if (isValid(m, n) && isValid(p, q) && array[m][n] && array[p][q]) {
                        array[m][n] = false;
                        array[p][q] = false;
                        array[i][j] = true;

                        if (puzzle2(array, remaining - 1, false)) {
                            solutionTrace.push(new Jump(new Location(m, n), new Location(i, j)));
                            printJump(solutionTrace.peek());
                            return true;
                        } else {
                            array[m][n] = true;
                            array[p][q] = true;
                            array[i][j] = false;
                        }
                    }
                }
            }
        }
        visited.add(id);
        return false;
    }

    public long idBoard(boolean[][] array) {
        long[] symmetry = new long[8];
        long mask = 1;
    
        for (int i0 = 0; i0 <= 6; ++i0) {
            for (int j0 = 0; j0 <= 6; ++j0) {
                int i1 = 6 - i0;
                int j1 = 6 - j0;
    
                if (array[i0][j0]) symmetry[0] |= mask;
                if (array[i1][j0]) symmetry[1] |= mask;
                if (array[i1][j1]) symmetry[2] |= mask;
                if (array[i0][j1]) symmetry[3] |= mask;
                if (array[j0][i0]) symmetry[4] |= mask;
                if (array[j1][i0]) symmetry[5] |= mask;
                if (array[j1][i1]) symmetry[6] |= mask;
                if (array[j0][i1]) symmetry[7] |= mask;
    
                mask = mask << 1;
            }
        }
    
        long representative = symmetry[0];
        for (int i = 1; i < 8; ++i) {
            if (symmetry[i] < representative) {
                representative = symmetry[i];
            }
        }
        
    return representative;
}
    

    public void printBoard(boolean[][] array) {
        System.out.println(" ---------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                if (array[i][j]) {
                    System.out.print("x ");
                } else if (isValid(i, j)) {
                    System.out.print("o ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println("|");
        }
        System.out.println(" ---------------");
    }

    public boolean solutionFound(boolean[][] array) {
        return array[LAST_I][LAST_J];
    }

    public boolean isValid(int i, int j) {
        return (i >= 0 && i <= 6) && (j >= 0 && j <= 6) && (
            (i >= 2 && i <= 4) || (j >= 2 && j <= 4) );
    }

    public void init(boolean[][] array) {
        for ( int i = 0; i <= 6; ++i ) {
            for ( int j = 0; j <= 6; ++j ) {
                array[i][j] = false;
            }
        }
    
        for ( int i = 0; i < POINTS; ++i ) {
            array[points[i][0]][points[i][1]] = true;
        }
    
        array[FIRST_I][FIRST_J] = false;
    }

    public void printJump(Jump jump) {
        System.out.println("Jump from (" + jump.from.i + "," + jump.from.j + ") to (" + jump.to.i + "," + jump.to.j + ")");
    }
}
