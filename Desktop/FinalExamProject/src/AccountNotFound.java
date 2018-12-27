
/** **********************************************************
            * Issa Ouedraogo
            * CISC 3115 MY9
            * Prof C.Zeigler
            * FinalExam-Part1
 ************************************************************* */
public class AccountNotFound extends Exception {

    public AccountNotFound() {
        super("Error: Account Not Found");
    }

    public AccountNotFound(int AcctNum) {
        super("Error: Account Not Found"
                + "the Account Number Enter does not Existe");
    }
}