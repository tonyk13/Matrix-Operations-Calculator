import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.Math;

/**
 * <p>
 * <b>The Matrices Project</b>
 * </p>
 *
 * <p>
 * <i>Current features:</i> create, random, print, add, subtract, multiply, dot product, rref, eigenvalues of 2x2
 * </p>
 *
 * <p>
 * <i>In progress:</i> eigenvalues of 3x3, cubic equation solver
 * </p>
 *
 * <p>
 * <i>Bugs:</i> trace for 3x3 does not work when attempting to find eigenvalues beforehand, "null" is printed when
 * attempting to find eigenvalues of 3x3, addition doesn't work, subtraction doesn't work
 * </p>
 *
 * @author Tony Kareeparampil
 */
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
                            System.out.println("\nCustom matrix created!\n");
                            matrices.get(matrices.size() - 1).print();
                        } else {
                            throw new IllegalArgumentException("User entered an invalid option.");
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
                            System.out.println("\nRandom matrix created!\n");
                            matrices.get(matrices.size() - 1).print();
                        } else {
                            throw new IllegalArgumentException("User entered an invalid option.");
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
                                    throw new IllegalArgumentException("Matrix doesn't exist.");
                                } else {
                                    System.out.println("\nSelect second matrix: ");
                                    int secondMatrixInput =
                                            Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                    if (secondMatrixInput < 1 || secondMatrixInput > matrices.size()) {
                                        throw new IllegalArgumentException("Matrix doesn't exist.");
                                    } else { //add the matrices
                                        matrices.add(matrices.get(firstMatrixInput - 1).add(matrices.get(secondMatrixInput - 1)));
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
                                            int randomInt = (int) (Math.random() * 2) + 1;

                                            if (randomInt == 1) {
                                                System.out.println("Sum saved!");
                                                return;
                                            } else if (randomInt == 2) {
                                                matrices.remove(matrices.size() - 1);
                                            }
                                        } else {
                                            throw new IllegalArgumentException("User entered an invalid option.");
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
                                    throw new IllegalArgumentException("Matrix doesn't exist.");
                                } else {
                                    System.out.println("\nSelect second matrix: ");
                                    int secondMatrixInput =
                                            Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                    if (secondMatrixInput < 1 || secondMatrixInput > matrices.size()) {
                                        throw new IllegalArgumentException("Matrix doesn't exist.");
                                    } else { //subtract the matrices
                                        matrices.add(matrices.get(firstMatrixInput - 1).subtract(matrices.get(secondMatrixInput - 1)));
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
                                            throw new IllegalArgumentException("User entered an invalid option.");
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
                                    throw new IllegalArgumentException("Matrix doesn't exist.");
                                } else {
                                    System.out.println("\nSelect second matrix: ");
                                    int secondMatrixInput =
                                            Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

                                    if (secondMatrixInput < 1 || secondMatrixInput > matrices.size()) {
                                        throw new IllegalArgumentException("Matrix doesn't exist.");
                                    } else { //multiply the matrices
                                        matrices.add(matrices.get(firstMatrixInput - 1).multiply(matrices.get(secondMatrixInput - 1)));
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
                                            throw new IllegalArgumentException("User entered an invalid option.");
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
                                    throw new IllegalArgumentException("Matrix doesn't exist.");
                                } else {
                                    matrices.add(matrices.get(matrixInput - 1).rref());
                                    System.out.println("\nReduced row echelon form: ");
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
                                        throw new IllegalArgumentException("User entered an invalid option.");
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
                                    throw new IllegalArgumentException("Matrix doesn't exist.");
                                } else if (matrices.get(matrixInput - 1).getNumRows() != 2 && matrices.get(matrixInput - 1).getNumCols() != 2) {
                                    throw new IncompatibleMatricesException();
                                } else {
                                    //find the eigenvalues
                                    ArrayList<Double> eigenvalues = matrices.get(matrixInput - 1).eigenvalues();
                                    System.out.println("\nEigenvalues: ");
                                    for (double value : eigenvalues) {
                                        System.out.print("λ = ");
                                        System.out.print(value);
                                        System.out.println();
                                    }
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("User entered an invalid option.");
                        }
                    } else if (menuInput.equalsIgnoreCase("5")) {
                        //Quit program

                        System.out.println("\nBy Tony K");
                        System.out.println("\nProgram terminating normally.");
                        quitProgram = true;
                    } else {
                        throw new IllegalArgumentException("User entered an invalid option.");
                    }
                } catch (IllegalArgumentException | NoMatricesException | IncompatibleMatricesException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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