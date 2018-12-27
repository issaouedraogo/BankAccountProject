
/** **********************************************************
 * Issa Ouedraogo
 * CISC 3115 MY9
 * Prof C.Zeigler
 * FinalExam-Part1
 ************************************************************* */
public abstract class BankAccount {

    // protected data member
    protected int acctNumber;
    protected double acctBalance;

    // No-Argument Constructor
    public BankAccount() {
        acctNumber = 0;
        acctBalance = 0.00;
    }
    // parametize constructor

    public BankAccount(int acctNum, double acctBal) {
        acctNumber = acctNum;
        acctBalance = acctBal;
    }
    // Copy constructo

    public BankAccount(BankAccount myAccount) {
        acctNumber = myAccount.acctNumber;
        acctBalance = myAccount.acctBalance;
    }

    //Abstract Methods
    public abstract double getBalance();

    public abstract void makeDeposit(double amountToDeposit)
            throws NegativeAmountEnter;

    public abstract void makeWithdrawal(double amountToWithdrawal)
            throws NegativeAmountEnter, InsufficientFunds;

}
