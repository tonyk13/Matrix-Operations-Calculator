import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the <code>Matrix</code> object.
 */
public class Matrix {
    public double[][] matrix;
    private final double[] rows;
    private final double[] cols;

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
     * Function to get cofactor of matrix[p][q] in temp[][]. n is the current dimension of matrix[][].
     *
     * @param matrix the matrix used in finding the cofactor
     * @param temp   temporary matrix
     * @param p      represents row index
     * @param q      represents column index
     * @param n      matrix.length
     */
    static void getCofactor(double[][] matrix, double[][] temp, int p, int q, int n) {
        int i = 0;
        int j = 0;

        // Looping for each element
        // of the matrix
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Copying into temporary matrix
                // only those element which are
                // not in given row and column
                if (row != p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    // Row is filled, so increase
                    // row index and reset col index
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /**
     * Finds the determinant of this matrix.
     *
     * @return A <code>double</code> representing the determinant of the matrix
     */
    public static double determinant(double[][] matrix, int n) {
        int N = matrix.length;

        int D = 0; // Initialize result

        // Base case : if matrix
        // contains single element
        if (n == 1)
            return matrix[0][0];

        // To store cofactors
        double[][] temp = new double[N][N];

        // To store sign multiplier
        int sign = 1;

        // Iterate for each element of first row
        for (int f = 0; f < n; f++) {
            // Getting Cofactor of mat[0][f]
            getCofactor(matrix, temp, 0, f, n);
            D += sign * matrix[0][f]
                    * determinant(temp, n - 1);

            // terms are to be added
            // with alternate sign
            sign = -sign;
        }
        return D;
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

    public void createMatrix() {
        matrix = new double[(int) getNumRows()][(int) getNumCols()];
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        for (int i = 1; i <= getNumRows(); i++) {
            for (int j = 1; j <= getNumCols(); j++) {
                System.out.print("Enter value for M" + i + "," + j + ": ");
                matrix[i - 1][j - 1] = scanner.nextDouble();
            }
            System.out.println();
        }
    }

    /**
     * Returns a 2D array that represents a random matrix created using Math.random to fill the spots.
     */
    public void randomMatrix() {
        matrix = new double[(int) getNumRows()][(int) getNumCols()];
        for (int i = 1; i <= getNumRows(); i++) {
            for (int j = 1; j <= getNumCols(); j++) {
                matrix[i - 1][j - 1] = (int) (Math.random() * 10);
            }
        }
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
        if (this.getNumRows() != other.getNumRows() && this.getNumCols() != other
                .getNumCols()) {
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
        if (this.getNumRows() != other.getNumRows() && this.getNumCols() != other
                .getNumCols()) {
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
            long determinant = (long) determinant(matrix, (int) getNumRows());
            long squaresTrace = (long) Operations.trace(squareTheMatrix());


            //now use the characteristic polynomial to solve for the eigenvalues
        }
        return eigenvalues;
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
}