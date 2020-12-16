import java.util.ArrayList;
import java.util.Arrays;

public class Operations {
    public static void print(Matrix matrix) {
        for (int i = 1; i <= matrix.getNumRows(); i++) {
            for (int j = 1; j <= matrix.getNumCols(); j++) {
                System.out.print("[" + matrix.matrix[i - 1][j - 1] + "]");
            }
            System.out.println();
        }
    }

    public static Matrix add(Matrix a, Matrix b) {
        Matrix sum = new Matrix(a.getNumRows(), a.getNumCols());
        sum.temp(a.getNumRows(), a.getNumCols());
        for (int i = 0; i < a.getNumRows(); i++) {
            for (int j = 0; j < a.getNumCols(); j++) {
                sum.matrix[i][j] = a.matrix[i][j] + b.matrix[i][j];
            }
        }
        return sum;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix difference = new Matrix(a.getNumRows(), a.getNumCols());
        difference.temp(a.getNumRows(), a.getNumCols());
        for (int i = 0; i < a.getNumRows(); i++) {
            for (int j = 0; j < a.getNumCols(); j++) {
                difference.matrix[i][j] = a.matrix[i][j] - b.matrix[i][j];
            }
        }
        return difference;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix product = new Matrix(a.getNumRows(), b.getNumCols());
        product.temp(a.getNumRows(), b.getNumCols());
        for (int i = 0; i < a.getNumRows(); i++) {
            for (int j = 0; j < b.getNumCols(); j++) {
                for (int k = 0; k < a.getNumCols(); k++) {
                    product.matrix[i][j] += a.matrix[i][k] * b.matrix[k][j];
                }
            }
        }
        return product;
    }

    public static double dotProduct(Matrix a, Matrix b) {
        double dotProduct = 0;
        for (int i = 0; i < a.getNumRows() - 1; i++) {
            for (int j = 0; j < a.getNumCols() - 1; j++) {
                dotProduct += a.matrix[i][j] * b.matrix[i][j];
            }
        }
        return dotProduct;
    }

    public static Matrix rref(Matrix a) { //reduced row echelon form
        Matrix rref = new Matrix(a.getNumRows(), a.getNumCols());
        rref.temp(a.getNumRows(), a.getNumCols());
        for (int i = 0; i < rref.matrix.length; ++i) {
            System.arraycopy(a.matrix[i], 0, rref.matrix[i], 0, rref.matrix[i].length);
        }
        for (int p = 0; p < rref.matrix.length; ++p) {
            /* Make this pivot 1 */
            double pv = rref.matrix[p][p];
            if (pv != 0) {
                double pvInv = 1.0 / pv;
                for (int i = 0; i < rref.matrix[p].length; ++i) {
                    rref.matrix[p][i] *= pvInv;
                }
            }
            /* Make other rows zero */
            for (int r = 0; r < rref.matrix.length; ++r) {
                if (r != p) {
                    double f = rref.matrix[r][p];
                    for (int i = 0; i < rref.matrix[r].length; ++i) {
                        rref.matrix[r][i] -= f * rref.matrix[p][i];
                    }
                }
            }
        }
        return rref;
    }

    public static ArrayList<Double> eigenvalues(Matrix matrix) { //currently only works for 2x2 matrices
        ArrayList<Double> eigenvalues = new ArrayList<>();
        if (matrix.getNumRows() == 2) { //finds eigenvalues for 2x2 matrix
            double b = -1 * (matrix.matrix[0][0] + matrix.matrix[1][1]);
            double c = matrix.matrix[0][0] * matrix.matrix[1][1] - matrix.matrix[1][0] * matrix.matrix[0][1];
            eigenvalues.addAll(quadraticSolver(1, b, c));
        } else if (matrix.getNumRows() == 3) { //finds eigenvalues for 3x3 matrix
            double a = -1;
            double b = Operations.trace(matrix);
            double c = .5 * (b * b - Operations.trace(squareTheMatrix(matrix)));
            double d = determinant(matrix);
            //now use the characteristic polynomial to solve for the eigenvalues
        }
        return eigenvalues;
    }

    public static double determinant(Matrix matrix) { //currently only works for 2x2 and 3x3 matrices
        double det = 0;
        if (matrix.getNumRows() == 2) {
            det = matrix.matrix[0][0] * matrix.matrix[1][1] - matrix.matrix[0][1] * matrix.matrix[1][0];
        } else if (matrix.getNumRows() == 3) {
            double x = (matrix.matrix[1][1] * matrix.matrix[2][2]) - (matrix.matrix[2][1] * matrix.matrix[1][2]);
            double y = (matrix.matrix[1][0] * matrix.matrix[2][2]) - (matrix.matrix[2][0] * matrix.matrix[1][2]);
            double z = (matrix.matrix[1][0] * matrix.matrix[2][1]) - (matrix.matrix[2][0] * matrix.matrix[1][1]);
            det = (matrix.matrix[0][0] * x) - (matrix.matrix[0][1] * y) + (matrix.matrix[0][2] * z);
        }
        return det;
    }

    public static double trace(Matrix matrix) {
        double tr = 0;
        for (int i = 0; i < matrix.getNumRows(); i++) {
            tr += matrix.matrix[i][i];
        }
        return tr;
    }

    public static Matrix squareTheMatrix(Matrix matrix) { //fix this shit
        Matrix squared = new Matrix(matrix.getNumRows(), matrix.getNumCols());
        squared.matrix = squared.emptyMatrix();
        for (int i = 0; i < matrix.getNumRows(); i++) {
            for (int j = 0; j < matrix.getNumCols(); j++) {
                squared.matrix[i][j] = matrix.matrix[i][j] * matrix.matrix[i][j];
            }
        }
        return squared;
    }

    public static Matrix copyMatrix(Matrix matrix) {
//        double[][] copy = new double[matrix.matrix.length][];
//        for (int i = 0; i < matrix.matrix.length; i++) {
//            double[] aMatrix = matrix.matrix[i];
//            double aLength = aMatrix.length;
//            copy[i] = new double[(int) aLength];
//            System.arraycopy(aMatrix, 0, copy[i], 0, (int) aLength);
//        }
//        Matrix realCopy = new Matrix(copy);
//        return realCopy;
//        Matrix copy = new Matrix(matrix.getNumRows(), matrix.getNumCols());
//        for (int i = 0; i < matrix.getNumRows(); i++) {
//            for (int j = 0; j < matrix.getNumCols(); j++) {
//                copy.matrix[i][j] = matrix.matrix[i][j];
//            }
//        }
        double[][] copy = Arrays.stream(matrix.matrix).map(double[]::clone).toArray(double[][]::new);
        Matrix copied = new Matrix(copy);
        return copied;
    }

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

//    public static ArrayList<Double> cubicSolver(double a, double b, double c, double d) {
//
//    }

    public static void printArraylist(ArrayList<Double> ArrayList) {
        for (Double number : ArrayList) {
            System.out.println(number);
        }
    }
}