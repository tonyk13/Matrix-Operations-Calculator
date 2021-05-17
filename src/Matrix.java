import java.util.Scanner;

/**
 * This class represents the <code>Matrix</code> object.
 */
public class Matrix {
    private double[] rows;
    private double[] cols;
    public double[][] matrix;

    /**
     * Constructor that creates a <code>Matrix</code> object using the number of rows and columns.
     *
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public Matrix(double rows, double cols) {
        this.rows = new double[(int) rows];
        this.cols = new double[(int) cols];
    }

    /**
     * Constructor that turns a 2D array into a <code>Matrix</code> object.
     *
     * @param matrixSorta a 2D array representing a matrix
     */
    public Matrix(double[][] matrixSorta) {
        double[][] myInt = new double[matrixSorta.length][];
        for (int i = 0; i < matrixSorta.length; i++) {
            myInt[i] = matrixSorta[i].clone();
        }
    }

    /**
     * Returns a 2D array that represents a temporary matrix during operations.
     *
     * @param rows number of rows in the matrix
     * @param cols number of columns in the matrix
     * @return The temporary matrix
     */
    public double[][] temp(double rows, double cols) {
        matrix = new double[(int) rows][(int) cols];
        return matrix;
    }

    /**
     * Creates a matrix filled with zeros.
     *
     * @return An empty matrix
     */
    public double[][] emptyMatrix() {
        matrix = new double[(int) getNumRows()][(int) getNumCols()];
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    public double[][] createMatrix() {
        matrix = new double[(int) getNumRows()][(int) getNumCols()];
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        for (int i = 1; i <= getNumRows(); i++) {
            for (int j = 1; j <= getNumCols(); j++) {
                System.out.print("Enter value for M" + i + "," + j + ": ");
                matrix[i - 1][j - 1] = scanner.nextDouble();
            }
            System.out.println();
        }
        return matrix;
    }

    /**
     * Returns a 2D array that represents a random matrix created using Math.random to fill the spots.
     *
     * @return A random matrix
     */
    public double[][] randomMatrix() {
        matrix = new double[(int) getNumRows()][(int) getNumCols()];
        for (int i = 1; i <= getNumRows(); i++) {
            for (int j = 1; j <= getNumCols(); j++) {
                matrix[i - 1][j - 1] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    /**
     * Returns the number of rows.
     *
     * @return The number of rows
     */
    public double getNumRows() {
        return this.rows.length;
    }

    /**
     * Returns the number of columns.
     *
     * @return The number of columns
     */
    public double getNumCols() {
        return this.cols.length;
    }

    /**
     * Prints the matrix.
     */
    public void print() {
        for (int i = 1; i <= getNumRows(); i++) {
            for (int j = 1; j <= getNumCols(); j++) {
                System.out.print("[" + matrix[i - 1][j - 1] + "]");
            }
            System.out.println();
        }
    }
}