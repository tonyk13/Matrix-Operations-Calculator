import java.util.Scanner;

public class Matrix {
    public double[] rows;
    public double[] cols;
    public double[][] matrix;

    public Matrix(double rows, double cols) {
        this.rows = new double[(int) rows];
        this.cols = new double[(int) cols];
    }

    public double[][] temp(double rows, double cols) { //used in Operations methods
        matrix = new double[(int) rows][(int) cols];
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

    public double[][] randomMatrix() {
        matrix = new double[(int) getNumRows()][(int) getNumCols()];
        for (int i = 1; i <= getNumRows(); i++) {
            for (int j = 1; j <= getNumCols(); j++) {
                matrix[i - 1][j - 1] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    public double getNumRows() {
        return this.rows.length;
    }

    public double getNumCols() {
        return this.cols.length;
    }

    public void print() {
        for (int i = 1; i <= getNumRows(); i++) {
            for (int j = 1; j <= getNumCols(); j++) {
                System.out.print("[" + matrix[i - 1][j - 1] + "]");
            }
            System.out.println();
        }
    }
}