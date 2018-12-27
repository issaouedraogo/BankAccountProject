
/** **********************************************************
                * Issa Ouedraogo
                * CISC 3115 MY9
                * Prof C.Zeigler
                * FinalExam-Part1
 ************************************************************* */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FinalExam {

    public static void main(String[] args)
            throws FileNotFoundException, AccountNotFound, 
	   NegativeAmountEnter, InsufficientFunds {

        // ArrayList of Savings
        ArrayList<SavingsAccount> myAccount = 
			new ArrayList<SavingsAccount>();
        char choice;// menu selection choice
        boolean not_done = true;// loop control flag
        //final double rate= 0.5;
        // Scanncer Object
        Scanner keyboard = new Scanner(System.in);
        // PrintWriter Object
        PrintWriter outFile = new PrintWriter("myOutput.txt");
        // call readAccts
        readAcct(myAccount);
        // call printAccts
        printAccts(myAccount, outFile);

        // do the loop for menu selection
        do {
            printMenu();
            choice = keyboard.next().charAt(0);
            switch (choice) {
                case 'q':
                case 'Q':
                    not_done = false;
                    printAccts(myAccount, outFile);
                    break;
                case 'b':
                case 'B':
                    getBalance(myAccount, keyboard, outFile);
                    break;
                case 'd':
                case 'D':
                    makeDeposit(myAccount, keyboard, outFile);
                    break;
                case 'i':
                case 'I':
                    addInterest(myAccount, keyboard, outFile);
                    break;
                case 'w':
                case 'W':
                    makeWithdrawl(myAccount, keyboard, outFile);
                    break;
                default:
                    outFile.println("Error: " + choice + 
                            " is an invalid selection -  try again");
                    outFile.println();
                    outFile.flush();
                    break;
            }
        } while (not_done);
        outFile.close();
        keyboard.close();

        System.out.println();
        System.out.println("The program is terminating....");
        outFile.flush();
    }

    /**
     * Method readAccts() 
     * Input: 
        * myAcct : ArrayList of Savings Account 
     * Process:
        * Reads the initial database of Bank Account 
     * Output: Fills in the initial account number and balance
	 
     */
    public static void readAcct(ArrayList<SavingsAccount> myAccts) 
            throws FileNotFoundException {

        String line;
        // open the bank account input file
        File dbFile = new File("myAccts.txt");
        // create Scanner object to read in the input file
        Scanner reader = new Scanner(dbFile);
        while (reader.hasNext()) {
            // get the line
            line = reader.nextLine();
            // Tokenize each line using a StringTokenizer
            StringTokenizer myToken = new StringTokenizer(line);
            // extract the data from the line just read
            SavingsAccount temp = new SavingsAccount(
                    Integer.parseInt(myToken.nextToken()),
                    Double.parseDouble(myToken.nextToken()));
            SavingsAccount mySavings = new SavingsAccount(temp);
            myAccts.add(mySavings);
        }
        reader.close();
    }

    /* Method printAccts:
	 * Input:
	 *  myAcct - ArrayList of Savings accounts 
	 *  outFile - reference to the output file
	 * Process:
	 *  Prints the database account numbers and and balances
	 * Output:
	 *  Prints the database account numbers and and balances
     */
    public static void printAccts(ArrayList<SavingsAccount> myAcct, 
            PrintWriter outFile) {
        outFile.println("\t\tBank Accounts in the Database");
        outFile.println();
        // print table column headings
        outFile.printf("%-8s%-7s", "Account_Number ", " Account_Balance ");
        outFile.println();
        for (int i = 0; i < myAcct.size(); i++) {
            outFile.printf("\t%-16s", myAcct.get(i).getAcctNumer());
            outFile.printf("$%7.2f", myAcct.get(i).getBalance());
            outFile.println();
            outFile.println("--------------------------------");
        }
        outFile.println();
        outFile.flush();
    }

    /*
	 * Method menu() 
	 * Input: none 
	 * Process: Prints the menu of transaction choices
	 * Output: Prints the menu of transaction choices
     */
    public static void printMenu() {
        System.out.println();
        System.out.println("Select one of the following transactions:");
        System.out.println("\t****************************");
        System.out.println("\t    List of Choices         ");
        System.out.println("\t****************************");
        System.out.println("\t     Q -- Quit the program");
        System.out.println("\t     B -- Get Account Balance");
        System.out.println("\t     D -- Make Deposit");
        System.out.println("\t     W -- Make Withdrawal");
        System.out.println("\t     I -- Add Interest");
        System.out.println();
        System.out.print("\tEnter your selection: ");
    }

    public static void getBalance(ArrayList<SavingsAccount> myAcct,
            Scanner kybd, PrintWriter outFile) throws AccountNotFound {
        int requestedAccount = 0;
        int index;
        try {
            // prompt for account number
            System.out.print("Enter the account number: ");
            // read-in the account number
            requestedAccount = kybd.nextInt();
            index = findAccts(myAcct, requestedAccount);
            outFile.println();
            outFile.println("Transaction Requested: Balance Inquiry");
            outFile.println("Account Number: " + requestedAccount);
            outFile.printf("Current Balance: $%.2f\n",
                    myAcct.get(index).getBalance());
            outFile.println();
        } catch (AccountNotFound e) {
            outFile.println("Transaction Requested: Balance Inquiry");
            outFile.println("Account Number: " + requestedAccount);
            outFile.println(e.getMessage());
        }
        outFile.println();
        outFile.flush();
    }

    /*
    * Method deposit: 
    * Input: myAcct - arrayList of Savingsaccounts 
     * outFile - reference to the output file 
     * kybd - reference to the "test cases" input file
    * Process: Prompts for the requested account Calls findacct() to see if the
    * account exists If the account exists, prompts for the amount to deposit 
    * If the amount is valid, it makes the deposit and prints the new balance
    * Otherwise, an error message is printed 
    * Output: For a valid deposit, the deposit transaction is printed 
     *Otherwise, an error message is printed
     */
    public static void makeDeposit(ArrayList<SavingsAccount> myAcct, 
            Scanner kybd, PrintWriter outFile)
            throws AccountNotFound, NegativeAmountEnter {
        int requestedAccount = 0;
        int index;
        double amountToDepo;

        try {
            // prompt for account number
            System.out.println("Enter the account number: "); 
            requestedAccount = kybd.nextInt(); // read-in the account number
            index = findAccts(myAcct, requestedAccount);
            System.out.println("Enter the amount of Deposit: ");
            amountToDepo = kybd.nextDouble();
            double old_balance = myAcct.get(index).getBalance();
            myAcct.get(index).makeDeposit(amountToDepo);
            outFile.println();
            outFile.println("Transaction Requested: Make Deposit");
            outFile.println("Account Number: " + requestedAccount);
            outFile.printf("Old Balance: $%.2f\n", old_balance);
            outFile.println("Deposite Amount: " + amountToDepo);
            outFile.printf("New Balance: $%.2f\n", 
                    myAcct.get(index).getBalance());
            outFile.println();
        } catch (AccountNotFound e) {
            outFile.println("Transaction Requested: Make Deposit");
            outFile.println("Account Number: " + requestedAccount);
            outFile.println(e.getMessage());
        } catch (NegativeAmountEnter e) {
            outFile.println("Transaction Requested: Make Deposit");
            outFile.println("Account Number: " + requestedAccount);
            outFile.println(e.getMessage());
        }
        outFile.println();
        outFile.flush();
    }

    /*
    * Method Withdrawal: 
    * Input: 
            * myAcct - arrayList of Savings accounts 
            * outFile - reference to the output file 
            * kybd - reference to the keyboard input 
    * Process:
           * Prompts for the requested account for withdrawal 
           * Calls findacct() to see if the account exists 
           * If the account exists, prompts for the amount for the
           * withdrawal 
           * If the account is not negative and has sufficient fund, 
           * it makes the withdrawal and prints the new balance 
           * Otherwise, an error message is printed 
    * Output: For a valid account and fund, 
           * the withdrawal transaction is printed 
           * Otherwise, an error message is printed
     */
    public static void makeWithdrawl(ArrayList<SavingsAccount> myAcct,
            Scanner kybd, PrintWriter outFile) throws AccountNotFound,
            NegativeAmountEnter, InsufficientFunds {
        int requestedAccount = 0;
        int index;
        double amountToWith;

        try {
            System.out.println("Enter the account number: ");
            requestedAccount = kybd.nextInt();
            index = findAccts(myAcct, requestedAccount);
            System.out.println("Enter the amount of Withdrawl: ");
            amountToWith = kybd.nextDouble();
            double old_balance = myAcct.get(index).getBalance();
            // call the makeWithdrawal
            myAcct.get(index).makeWithdrawal(amountToWith);
            outFile.println();
            outFile.println("Transaction Requested: Make Withdrawal");
            outFile.println("Account Number: " + requestedAccount);
            outFile.printf("Withdrawal Amount: " + amountToWith);
            outFile.printf("Old Balance: $%.2f\n", old_balance);
            outFile.printf("New Balance: $%.2f\n",
                    myAcct.get(index).getBalance());
            outFile.println();
        } catch (AccountNotFound e) {
            outFile.println("Transaction Requested: Make Withdrawal");
            outFile.println("Account Number: " + requestedAccount);
            outFile.println(e.getMessage());
        } catch (NegativeAmountEnter e) {
            outFile.println("Transaction Requested: Make Withdrawal");
            outFile.println("Account Number: " + requestedAccount);
            outFile.println(e.getMessage());
        } catch (InsufficientFunds e) {
            outFile.println("Transaction Requested: Make Withdrawal");
            outFile.println("Account Number: " + requestedAccount);
            outFile.println(e.getMessage());
        }
        outFile.println();
        outFile.flush();
    }
    /*
    * Method addInterest: 
    * Input: 
            * myAcct - arrayList of Savings accounts 
            * outFile - reference to the output file 
            * kybd - reference to the keyboard input 
    * Process:
           * Prompts for the requested account to add Interest 
           * Calls findacct() to see if the account exists 
           * If the account exists, prompts for the Interest rate
           * add rate * current balance to current balance
           * Otherwise, an AccountNotFound error message is printed 
    * Output: For a valid account and fund, 
           * the addInterest transaction is printed 
           * Otherwise, an error message is printed
     */
    public static void addInterest(ArrayList<SavingsAccount> myAcct,
            Scanner kybd, PrintWriter outFile) {
        int requestedAcctNum = 0;
        int index;
        double rate;
        try {
            System.out.println("Enter the Account Number: ");
            requestedAcctNum = kybd.nextInt();
            index = findAccts(myAcct, requestedAcctNum);
            //System.out.println("Enter the Interest Rate: ");
            rate = kybd.nextDouble();
            double old_balance = myAcct.get(index).getBalance();
            myAcct.get(index).addInterest(rate);
            outFile.println("Transaction Requested: Add Interest ");
            outFile.println("Account Number: " + requestedAcctNum);
            outFile.printf("Old Balance: $%.2f", old_balance);
            outFile.printf("Interest Rate: ", rate);
           outFile.printf("New Balance: $%.2f",myAcct.get(index).getBalance());
        } catch (AccountNotFound e) {
            outFile.println("Transaction Requested: Add Interest ");
            outFile.println("Account Number: " + requestedAcctNum);
            outFile.println(e.getMessage());
        }
        outFile.println();
        outFile.flush();
    }

    /*
    * Method findAcct: 
    * Input: 
           * bankaccts - array of bank accounts 
           * requestedAccount - requested account requested_number
    * Process: 
           * Performs a linear search on the bank account array 
           * for the requested account number 
    *Output: If found, the index of the requested account is
           * returned Otherwise, returns -1
    */
    public static int findAccts(ArrayList<SavingsAccount> myAcct, 
            int requestedAccount) throws AccountNotFound {
        for (int index = 0; index < myAcct.size(); index++) {

            if (myAcct.get(index).getAcctNumer() == requestedAccount) {
                return index;
            }
        }
        throw new AccountNotFound();
    }
}
