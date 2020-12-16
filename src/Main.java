//Tony Kareeparampil presents
//The Matrices Project
//Current features: create, random, print, add, subtract, multiply, dot product, rref, eigenvalues of 2x2
//Working on: eigenvalues of 3x3, third power polynomial solver(?)
//Bugs: trace for 3x3 is fucked when attempting to find eigenvalues beforehand

public class Main {
    public static void main(String[] args) {
        int n = 3; //size of matrix
        System.out.println("test1");
        Matrix test1 = new Matrix(n, n);
        test1.createMatrix();
//        System.out.println("Rows in test1: " + test1.getNumRows());
//        System.out.println("Columns in test1: " + test1.getNumCols());
        test1.print();
        System.out.println();

//        System.out.println("test2");
//        Matrix test2 = new Matrix(4, 4);
//        test2.randomMatrix();
//        System.out.println("Rows in test2: " + test2.getNumRows());
//        System.out.println("Columns in test2: " + test2.getNumCols());
//        test2.print();
//        System.out.println();
//
//        System.out.println("Sum of test1 and test2");
//        if (test1.getNumRows() == test2.getNumRows() && test1.getNumCols() == test2.getNumCols()) {
//            Operations.print(Operations.add(test1, test2));
//        } else {
//            System.out.println("Fuck off");
//        }
//        System.out.println();
//
//        System.out.println("Difference of test1 and test2");
//        if (test1.getNumRows() == test2.getNumRows() && test1.getNumCols() == test2.getNumCols()) {
//            Operations.print(Operations.subtract(test1, test2));
//        } else {
//            System.out.println("Fuck off");
//        }
//        System.out.println();
//
//        System.out.println("Product of test1 and test2");
//        if (test1.getNumCols() == test2.getNumRows()) {
//            Operations.print(Operations.multiply(test1, test2));
//        } else {
//            System.out.println("Fuck off");
//        }
//        System.out.println();
//
//        System.out.println("Dot product of test1 and test2");
//        System.out.println(Operations.dotProduct(test1, test2));
//        System.out.println();

//        System.out.println("rref of test1");
//        Operations.print(Operations.rref(test1));

        System.out.println("Eigenvalues of test1");
        Operations.printArraylist(Operations.eigenvalues(test1));
        System.out.println("Trace of test1");
        System.out.println(Operations.trace(test1));
    }
}