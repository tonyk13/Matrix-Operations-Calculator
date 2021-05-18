import java.util.ArrayList;
import java.util.Arrays;
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
        matrix = myInt;
    }

    /**
     * Returns a 2D array that represents a temporary matrix during operations.
     *
     * @param rows number of rows in the matrix
     * @param cols number of columns in the matrix
     * @return The temporary matrix
     */
    public double[][] temp(double rows, double cols) {
        return new double[(int) rows][(int) cols];
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

    /**
     * Adds two matrices.
     *
     * @param other the other matrix
     * @return The sum
     */
    public Matrix add(Matrix other) throws IncompatibleMatricesException {
        if (this.getNumRows() != other.getNumRows() && this.getNumCols() != other.getNumCols()) {
            throw new IncompatibleMatricesException();
        }

        Matrix sum = new Matrix(this.getNumRows(), this.getNumCols());
        sum.temp(getNumRows(), getNumCols());
        for (int i = 0; i < this.getNumRows(); i++) {
            for (int j = 0; j < this.getNumCols(); j++) {
                sum.matrix[i][j] = matrix[i][j] + other.matrix[i][j];
            }
        }
        return sum;
    }

    /**
     * Subtracts two matrices.
     *
     * @param other the other matrix
     * @return The difference
     */
    public Matrix subtract(Matrix other) throws IncompatibleMatricesException {
        if (this.getNumRows() != other.getNumRows() && this.getNumCols() != other.getNumCols()) {
            throw new IncompatibleMatricesException();
        }

        Matrix difference = new Matrix(this.getNumRows(), this.getNumCols());
        double[][] temp = difference.temp(this.getNumRows(), this.getNumCols());
        for (int i = 0; i < this.getNumRows(); i++) {
            for (int j = 0; j < this.getNumCols(); j++) {
                temp[i][j] = this.matrix[i][j] - other.matrix[i][j];
            }
        }
        return difference;
    }

    /**
     * Multiplies two matrices.
     *
     * @param other the other matrix
     * @return The product
     */
    public Matrix multiply(Matrix other) {
        Matrix product = new Matrix(this.getNumRows(), other.getNumCols());
        product.temp(this.getNumRows(), other.getNumCols());
        for (int i = 0; i < this.getNumRows(); i++) {
            for (int j = 0; j < other.getNumCols(); j++) {
                for (int k = 0; k < this.getNumCols(); k++) {
                    product.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        return product;
    }

    /**
     * Finds the reduced row echelon form of the matrix.
     *
     * @return The matrix in reduced row echelon form
     */
    public Matrix rref() {
        this.temp(this.getNumRows(), this.getNumCols());
        for (double[] doubles : this.matrix) {
            System.arraycopy(doubles, 0, doubles, 0, doubles.length);
        }
        for (int p = 0; p < this.matrix.length; ++p) {
            /* Make this pivot 1 */
            double pv = this.matrix[p][p];
            if (pv != 0) {
                double pvInv = 1.0 / pv;
                for (int i = 0; i < this.matrix[p].length; ++i) {
                    this.matrix[p][i] *= pvInv;
                }
            }
            /* Make other rows zero */
            for (int r = 0; r < this.matrix.length; ++r) {
                if (r != p) {
                    double f = this.matrix[r][p];
                    for (int i = 0; i < this.matrix[r].length; ++i) {
                        this.matrix[r][i] -= f * this.matrix[p][i];
                    }
                }
            }
        }
        return this;
    }

    /**
     * Finds the eigenvalues of the matrix. Currently only works for 2x2 matrices.
     *
     * @return An <code>ArrayList</code> with the eigenvalues
     */
    public ArrayList<Double> eigenvalues() {
        ArrayList<Double> eigenvalues = new ArrayList<>();
        if (getNumRows() == 2) { //finds eigenvalues for 2x2 matrix
            double b = -1 * (matrix[0][0] + matrix[1][1]);
            double c = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
            eigenvalues.addAll(Operations.quadraticSolver(1, b, c));
        } else if (getNumRows() == 3) { //finds eigenvalues for 3x3 matrix
            long trace = (long) Operations.trace(this);
            long determinant = (long) determinant();
            long squaresTrace = (long) Operations.trace(squareTheMatrix());


            //now use the characteristic polynomial to solve for the eigenvalues
        }
        return eigenvalues;
    }

    /**
     * Finds the determinant of this matrix. Compatibility: r,c <= 3.
     *
     * @return The determinant of the matrix
     */
    public double determinant() {
        double det = 0;
        if (getNumRows() == 2) {
            det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else if (getNumRows() == 3) {
            double x = (matrix[1][1] * matrix[2][2]) - (matrix[2][1] * matrix[1][2]);
            double y = (matrix[1][0] * matrix[2][2]) - (matrix[2][0] * matrix[1][2]);
            double z = (matrix[1][0] * matrix[2][1]) - (matrix[2][0] * matrix[1][1]);
            det = (matrix[0][0] * x) - (matrix[0][1] * y) + (matrix[0][2] * z);
        }
        return det;
    }

    /**
     * Squares the matrix.
     *
     * @return The squared matrix
     */
    public Matrix squareTheMatrix() { //fix this
        Matrix squared = new Matrix(getNumRows(), getNumCols());
        squared.matrix = squared.emptyMatrix();
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumCols(); j++) {
                squared.matrix[i][j] = matrix[i][j] * matrix[i][j];
            }
        }
        return squared;
    }

    public Matrix copyMatrix() {
        double[][] copy = Arrays.stream(matrix).map(double[]::clone).toArray(double[][]::new);
        return new Matrix(copy);
    }
}