/************************************************************
			Issa Ouedraogo
			CISC 3115 MY9
			Prof C.Zeigler
			FinalExam-Part1 
**************************************************************/
public class SavingsAccount extends BankAccount implements Interest{
	/// parametize constructor
	public SavingsAccount(int acctNum, double acctBal) {
		super(acctNum, acctBal);
	}
	// Copy constructo
	public SavingsAccount(SavingsAccount myAccount) {
		super(myAccount);
	}
	// Class methods 
	public int getAcctNumer() {
		return acctNumber;
	}
	@Override
	public double getBalance() {
		return acctBalance;
	}
	@Override
	public void makeDeposit(double amountToDeposit) 
			throws NegativeAmountEnter {
	   if(amountToDeposit <= 0) {
		throw new NegativeAmountEnter(amountToDeposit);
	    }
	    acctBalance = acctBalance +amountToDeposit;
		
	}
	@Override
	public void makeWithdrawal(double amountToWithdrawal) 
		throws NegativeAmountEnter, InsufficientFunds {
	  if(amountToWithdrawal <= 0) {
		throw new NegativeAmountEnter(amountToWithdrawal);
	   }
	   if(amountToWithdrawal > acctBalance) {
		throw new InsufficientFunds(amountToWithdrawal);
	   }
	   acctBalance = acctBalance -amountToWithdrawal;
	}
	@Override
	public void addInterest(double rate) {
	   acctBalance  = acctBalance +acctBalance*rate;
		
	}

}
