
/** **********************************************************
                    * Issa Ouedraogo
                    * CISC 3115 MY9
                    * Prof C.Zeigler
                    * FinalExam-Part1
 ************************************************************* */
public class NegativeAmountEnter extends Exception {

    public NegativeAmountEnter() {
        super("Error: Negative Amount Enter");
    }

    public NegativeAmountEnter(double amount) {
        super("Error: Negative Amount Enter\n "
                + amount);
    }

}
