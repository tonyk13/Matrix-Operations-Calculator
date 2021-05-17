//Tony Kareeparampil presents
//The Matrices Project
//Current features: create, random, print, add, subtract, multiply, dot product, rref, eigenvalues of 2x2
//Working on: eigenvalues of 3x3, third power polynomial solver(?)
//Bugs: trace for 3x3 is fucked when attempting to find eigenvalues beforehand

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.Math;

public class Main {
    private static boolean quitProgram = false;
    private static String menuInput;
    private static final ArrayList<Matrix> matrices = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to The Matrices Project!");

        try {
            while (!quitProgram) {
                try {
                    menuOptions();
                    if (menuInput.equalsIgnoreCase("1")) {
                        //Create a custom matrix

                        System.out.println();
                        System.out.println("1) 1x1");
                        System.out.println("2) 2x2");
                        System.out.println("3) 3x3");
                        System.out.println("Select matrix size");
                        int matrixSizeInput =
                                Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                        if (matrixSizeInput == 1 || matrixSizeInput == 2 || matrixSizeInput == 3) {
                            matrices.add(new Matrix(matrixSizeInput, matrixSizeInput));
                            matrices.get(matrices.size() - 1).createMatrix();
                            matrices.get(matrices.size() - 1).print();
                        } else {
                            throw new IllegalArgumentException("User entered an invalid input.");
                        }
                    } else if (menuInput.equalsIgnoreCase("2")) {
                        //Create a random matrix

                        System.out.println();
                        System.out.println("1) 1x1");
                        System.out.println("2) 2x2");
                        System.out.println("3) 3x3");
                        System.out.println("Select matrix size");
                        int matrixSizeInput =
                                Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                        if (matrixSizeInput == 1 || matrixSizeInput == 2 || matrixSizeInput == 3) {
                            matrices.add(new Matrix(matrixSizeInput, matrixSizeInput));
                            matrices.get(matrices.size() - 1).randomMatrix();
                            System.out.println();
                            System.out.println("Random matrix created!\n");
                            matrices.get(matrices.size() - 1).print();
                        } else {
                            throw new IllegalArgumentException("User entered an invalid input.");
                        }
                    } else if (menuInput.equalsIgnoreCase("3")) {
                        //Print all matrices

                        int i = 1;
                        for (Matrix matrix : matrices) {
                            System.out.println("\n" + i);
                            matrix.print();
                            i++;
                        }
                    } else if (menuInput.equalsIgnoreCase("4")) {
                        //Operations

                        System.out.println();
                        System.out.println("1) Add");
                        System.out.println("2) Subtract");
                        System.out.println("3) Multiply");
                        System.out.println("4) Find reduced row echelon form");
                        System.out.println("5) Find eigenvalues (2x2 only)");
                        System.out.println("6) Find determinant");
                        System.out.println("Select an operation: ");
                        int operationInput =
                                Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                        if (operationInput == 1) { //Add
                            if (matrices.size() == 0) {
                                throw new NoMatricesException();
                            } else {
                                int i = 1;
                                for (Matrix matrix : matrices) {
                                    System.out.println("\n" + i);
                                    matrix.print();
                                    i++;
                                }

                                System.out.println("\nSelect first matrix: ");
                                int firstMatrixInput =
                                        Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                if (firstMatrixInput < 1 || firstMatrixInput > matrices.size()) {
                                    throw new IllegalArgumentException("User entered an invalid input.");
                                } else {
                                    System.out.println("\nSelect second matrix: ");
                                    int secondMatrixInput =
                                            Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                    if (secondMatrixInput < 1 || secondMatrixInput > matrices.size()) {
                                        throw new IllegalArgumentException("User entered an invalid input.");
                                    } else { //add the matrices
                                        matrices.add(Operations.add(matrices.get(firstMatrixInput - 1),
                                                matrices.get(secondMatrixInput - 1)));
                                        System.out.println("\nSum: ");
                                        matrices.get(matrices.size() - 1).print();

                                        System.out.println("Do you want to save the sum?: ");
                                        System.out.println("1) Yes");
                                        System.out.println("2) No");
                                        System.out.println("3) Maybe"); //use Math.Random to generate an answer

                                        //Ask the user if they want to save the sum
                                        int saveSumInput =
                                                Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                        if (saveSumInput == 1) {
                                            return;
                                        } else if (saveSumInput == 2) {
                                            matrices.remove(matrices.size() - 1);
                                        } else if (saveSumInput == 3) {
                                            int cock = (int) (Math.random() * 2) + 1;

                                            if (cock == 1) {
                                                System.out.println("Sum saved!");
                                                return;
                                            } else if (cock == 2) {
                                                matrices.remove(matrices.size() - 1);
                                            }
                                        } else {
                                            throw new IllegalArgumentException("User entered an invalid input.");
                                        }
                                    }
                                }
                            }
                        } else if (operationInput == 2) { //Subtract
                            if (matrices.size() == 0) {
                                throw new NoMatricesException();
                            } else {
                                int i = 1;
                                for (Matrix matrix : matrices) {
                                    System.out.println("\n" + i);
                                    matrix.print();
                                    i++;
                                }

                                System.out.println("\nSelect first matrix: ");
                                int firstMatrixInput =
                                        Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                if (firstMatrixInput < 1 || firstMatrixInput > matrices.size()) {
                                    throw new IllegalArgumentException("User entered an invalid input.");
                                } else {
                                    System.out.println("\nSelect second matrix: ");
                                    int secondMatrixInput =
                                            Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                    if (secondMatrixInput < 1 || secondMatrixInput > matrices.size()) {
                                        throw new IllegalArgumentException("User entered an invalid input.");
                                    } else { //subtract the matrices
                                        matrices.add(Operations.subtract(matrices.get(firstMatrixInput - 1),
                                                matrices.get(secondMatrixInput - 1)));
                                        System.out.println("\nDifference: ");
                                        matrices.get(matrices.size() - 1).print();

                                        System.out.println("Do you want to save the difference?: ");
                                        System.out.println("1) Yes");
                                        System.out.println("2) No");
                                        System.out.println("3) Maybe"); //use Math.Random to generate an answer

                                        //Ask the user if they want to save the difference
                                        int saveDifferenceInput =
                                                Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                        if (saveDifferenceInput == 1) {
                                            return;
                                        } else if (saveDifferenceInput == 2) {
                                            matrices.remove(matrices.size() - 1);
                                        } else if (saveDifferenceInput == 3) {
                                            int random = (int) (Math.random() * 2) + 1;

                                            if (random == 1) {
                                                System.out.println("Difference saved!");
                                                return;
                                            } else if (random == 2) {
                                                matrices.remove(matrices.size() - 1);
                                            }
                                        } else {
                                            throw new IllegalArgumentException("User entered an invalid input.");
                                        }
                                    }
                                }
                            }
                        } else if (operationInput == 3) { //Multiply
                            if (matrices.size() == 0) {
                                throw new NoMatricesException();
                            } else {
                                int i = 1;
                                for (Matrix matrix : matrices) {
                                    System.out.println("\n" + i);
                                    matrix.print();
                                    i++;
                                }

                                System.out.println("\nSelect first matrix: ");
                                int firstMatrixInput =
                                        Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                if (firstMatrixInput < 1 || firstMatrixInput > matrices.size()) {
                                    throw new IllegalArgumentException("User entered an invalid input.");
                                } else {
                                    System.out.println("\nSelect second matrix: ");
                                    int secondMatrixInput =
                                            Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                    if (secondMatrixInput < 1 || secondMatrixInput > matrices.size()) {
                                        throw new IllegalArgumentException("User entered an invalid input.");
                                    } else { //multiply the matrices
                                        matrices.add(Operations.multiply(matrices.get(firstMatrixInput - 1),
                                                matrices.get(secondMatrixInput - 1)));
                                        System.out.println("\nProduct: "); //consume
                                        matrices.get(matrices.size() - 1).print();

                                        System.out.println("Do you want to save the product?: ");
                                        System.out.println("1) Yes");
                                        System.out.println("2) No");
                                        System.out.println("3) Maybe"); //use Math.Random to generate an answer

                                        //Ask the user if they want to save the product
                                        int saveProductInput =
                                                Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                        if (saveProductInput == 1) {
                                            return;
                                        } else if (saveProductInput == 2) {
                                            matrices.remove(matrices.size() - 1);
                                        } else if (saveProductInput == 3) {
                                            int random = (int) (Math.random() * 2) + 1;

                                            if (random == 1) {
                                                System.out.println("Product saved!");
                                                return;
                                            } else if (random == 2) {
                                                matrices.remove(matrices.size() - 1);
                                            }
                                        } else {
                                            throw new IllegalArgumentException("User entered an invalid input.");
                                        }
                                    }
                                }
                            }
                        } else if (operationInput == 4) { //RREF
                            if (matrices.size() == 0) {
                                throw new NoMatricesException();
                            } else {
                                int i = 1;
                                for (Matrix matrix : matrices) {
                                    System.out.println("\n" + i);
                                    matrix.print();
                                    i++;
                                }

                                System.out.println("\nSelect matrix: ");
                                int matrixInput =
                                        Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                if (matrixInput < 1 || matrixInput > matrices.size()) {
                                    throw new IllegalArgumentException("User entered an invalid input.");
                                } else {
                                    matrices.add(Operations.rref(matrices.get(matrixInput - 1)));
                                    System.out.println("\nReduced row echelon form: "); //Shoutout Lowell Jones
                                    matrices.get(matrices.size() - 1).print();

                                    System.out.println("Do you want to save the reduced row echelon form of the " +
                                            "matrix?: ");
                                    System.out.println("1) Yes");
                                    System.out.println("2) No");
                                    System.out.println("3) Maybe"); //use Math.Random to generate an answer

                                    //Ask the user if they want to save the rref
                                    int saveRrefInput =
                                            Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                    if (saveRrefInput == 1) {
                                        return;
                                    } else if (saveRrefInput == 2) {
                                        matrices.remove(matrices.size() - 1);
                                    } else if (saveRrefInput == 3) {
                                        int random = (int) (Math.random() * 2) + 1;

                                        if (random == 1) {
                                            System.out.println("Reduced row echelon form saved!");
                                            return;
                                        } else if (random == 2) {
                                            matrices.remove(matrices.size() - 1);
                                        }
                                    } else {
                                        throw new IllegalArgumentException("User entered an invalid input.");
                                    }
                                }
                            }
                        } else if (operationInput == 5) { //Eigenvalues
                            //I would like to thank Ben Wu for being a phenomenal TA in MAT 211 Fall 2020

                            if (matrices.size() == 0) {
                                throw new NoMatricesException();
                            } else {
                                for (int i = 0; i < matrices.size(); i++) {
                                    System.out.println("\n" + (i + 1));
                                    matrices.get(i).print();
                                }

                                System.out.println("\nThis feature currently only works for 2x2 matrices.");

                                System.out.println("\nSelect matrix: ");
                                int matrixInput =
                                        Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                if (matrixInput < 1 || matrixInput > matrices.size()) {
                                    throw new IllegalArgumentException("User entered an invalid input.");
                                } else if (matrices.get(matrixInput - 1).getNumRows() != 2 && matrices.get(matrixInput - 1).getNumCols() != 2) {
                                    throw new IncompatibleMatricesException();
                                } else {
                                     //find the eigenvalues
                                    ArrayList<Double> eigenvalues =
                                            Operations.eigenvalues(matrices.get(matrixInput - 1));

                                    System.out.println("\nEigenvalues: ");
                                    for (Double eigenvalue : eigenvalues) {
                                        System.out.println(eigenvalue);
                                    }
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("User entered an invalid input.");
                        }
                    } else if (menuInput.equalsIgnoreCase("5")) {
                        //Quit program

                        System.out.println("\nBy Tony K");
                        System.out.println("\nProgram terminating normally.");
                        quitProgram = true;
                    } else {
                        throw new IllegalArgumentException("User entered an invalid input.");
                    }
                } catch (IllegalArgumentException | NoMatricesException | IncompatibleMatricesException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        //
//        int n = 3; //size of matrix
//        System.out.println("test1");
//        Matrix test1 = new Matrix(n, n);
//        test1.createMatrix();
//        System.out.println("Rows in test1: " + test1.getNumRows());
//        System.out.println("Columns in test1: " + test1.getNumCols());
//        test1.print();
//        System.out.println();

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

//        System.out.println("Eigenvalues of test1");
//        Operations.printArraylist(Operations.eigenvalues(test1));
//        System.out.println("Trace of test1");
//        System.out.println(Operations.trace(test1));
//
//        Matrix copy = Operations.copyMatrix(test1);
//        copy.print();
    }

    public static void menuOptions() throws IOException {
        System.out.println();
        System.out.println("1) Create a custom matrix");
        System.out.println("2) Create a random matrix");
        System.out.println("3) Print all matrices");
        System.out.println("4) Operations");
        System.out.println("5) Quit");
        System.out.println("Select an option:");

        menuInput =
                new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}