import java.util.ArrayList;
//create an algorithm to solve cubic equation

/**
 * This class contains the matrix operations.
 */
public class Operations {
    public static double dotProduct(Matrix a, Matrix b) {
        double dotProduct = 0;
        for (int i = 0; i < a.getNumRows() - 1; i++) {
            for (int j = 0; j < a.getNumCols() - 1; j++) {
                dotProduct += a.matrix[i][j] * b.matrix[i][j];
            }
        }
        return dotProduct;
    }

    /**
     * Finds the trace of the matrix.
     *
     * @param matrix the matrix
     * @return A double representing the trace of the matrix
     */
    public static double trace(Matrix matrix) {
        double tr = 0;
        for (int i = 0; i < matrix.getNumRows(); i++) {
            tr += matrix.matrix[i][i];
        }
        return tr;
    }

    /**
     * Solves a quadratic equation.
     *
     * @param a a
     * @param b b
     * @param c c
     * @return An <code>ArrayList</code> with the roots
     */
    public static ArrayList<Double> quadraticSolver(double a, double b, double c) {
        ArrayList<Double> roots = new ArrayList<>();
        double result = b * b - 4.0 * a * c;
        if (result > 0.0) {
            double r1 = (-1.0 * b + Math.pow(result, 0.5)) / (2.0 * a);
            double r2 = (-1.0 * b - Math.pow(result, 0.5)) / (2.0 * a);
            roots.add(r1);
            roots.add(r2);
        } else if (result == 0.0) {
            double r1 = -1 * b / (2.0 * a);
            roots.add(r1);
        }
        return roots;
    }

    /**
     * Solves a cubic equation. This will be my biggest achievement to date.
     *
     * @param A first coefficient
     * @param B second coefficient
     * @param C third coefficient
     * @param D fourth coefficient
     * @return An <code>ArrayList</code> with the roots.
     */
    public static ArrayList<Long> cubicSolver(long A, long B, long C, long D, long E) {
        ArrayList<Long> solution = new ArrayList<>();

        // Initialise start and end
        long start = 0, end = 100000;

        long mid, ans;

        // Implement Binary Search
        while (start <= end) {
            // Find mid
            mid = start + (end - start) / 2;

            // Find the value of f(x) using
            // current mid
            ans = check(A, B, C, D, mid);

            // Check if current mid satisfy
            // the equation
            if (ans == E) {
                // Print mid and add to arraylist of solution
                System.out.println(mid);
                solution.add(mid);
            }

            if (ans < E) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return solution;
    }

    static long check(long A, long B, long C, long D, long x) {
        long ans;

        // Find the value equation at x
        ans = (A * x * x * x + B * x * x + C * x + D);

        // Return the value of ans
        return ans;
    }
}