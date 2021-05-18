public class IncompatibleMatricesException extends Exception {

    public IncompatibleMatricesException() {
        System.out.println("The selected matrix/matrices is/are not compatible " +
                "for this operation.");
    }
}