import java.util.ArrayList;
import java.util.Arrays;
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

//    /**
//     * Solves a cubic equation. This will be my biggest achievement to date.
//     *
//     * @param a a
//     * @param b b
//     * @param c c
//     * @param d d
//     * @return An <code>ArrayList</code> with the roots.
//     */
//    public static ArrayList<Double> cubicSolver(double a, double b, double c, double d) {
//
//    }
}