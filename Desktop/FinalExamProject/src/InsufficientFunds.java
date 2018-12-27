
/** ***************************************************
                * Issa Ouedraogo
                * CISC 3115 MY9
                * Prof C.Zeigler
                * FinalExam-Part1
 ***************************************************** */
public class InsufficientFunds extends Exception {

    public InsufficientFunds() {
        super("Error: Account has Insufficient Funds");
    }

    public InsufficientFunds(double amount) {
        super("Error: Account has Insufficient Funds"
                + " for this amount "
                + amount);
    }

}
