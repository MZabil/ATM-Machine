/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atm;
import java.util.Scanner;
/**
 *
 * @author Pakistan
 */
public class ATM {

    /**
     * @param args the command line arguments
     */
    
    //Creating a method for input from user
    static Scanner in = new Scanner (System.in);
    
    //To handle fund transfer between users
    int []TransferData = new int[2];
    
    public static void main (String[] args) {
        // TODO code application logic here
        //Creating an ATM
        ATM Transaction = new ATM();
        //Creating 5 accounts
        userData Ali = new userData(1);
        userData Hassan = new userData(2);
        userData Ahmed = new userData(3);
        userData Zain = new userData(4);
        userData Umer = new userData(5);
        
        //Performing tasks for current user
        int userNo;
        System.out.println("Enter user number: ");
        userNo = in.nextInt();
        switch(userNo){
            case 1 -> Ali.PinCheck(Transaction.TransferData);
            case 2 -> Hassan.PinCheck(Transaction.TransferData);
            case 3 -> Ahmed.PinCheck(Transaction.TransferData);
            case 4 -> Zain.PinCheck(Transaction.TransferData);
            case 5 -> Umer.PinCheck(Transaction.TransferData);
            default -> System.out.println("Wrong user number entered.");            
        }
        
        //Adding funds to receiver in case of transfer
        switch(Transaction.TransferData[1]){
            case 1 -> Ali.AddFunds(Transaction.TransferData[0]);
            case 2 -> Hassan.AddFunds(Transaction.TransferData[0]);
            case 3 -> Ahmed.AddFunds(Transaction.TransferData[0]);
            case 4 -> Zain.AddFunds(Transaction.TransferData[0]);
            case 5 -> Umer.AddFunds(Transaction.TransferData[0]);
        }
    }
    
}

//Creating sub-class to store user details
    class userData extends ATM{
        private float Funds;
        private final int pin;
        private int choice;
        
        //Creating constructor to set attribute values when account is created
        public userData(int userNumber){
            //Setting up accounts when created
            Funds= userNumber * 10000;
            pin= userNumber * 1234;
        }
        
        //Checking pin 
        public void PinCheck(int[]TransferData){
            int pass;
            System.out.println("Enter your pin: ");
            pass = in.nextInt();
            
            if (pin == pass)
            {
                Choice(TransferData);
            }
            else
                System.out.println("Incorrect pin entered.");
        }
        
        //Choice function
        public void Choice(int[]TransferData){
            
            do{
                    System.out.println("What function would you like to perform?");
                    System.out.println("Enter 1 for balance inquiry, 2 for cash withdrawal or 3 for fund transfer");
                    choice = in.nextInt();
                
                    if (choice==1)
                        BalanceInquiry();
                    if (choice==2)
                        CashWithdrawal();
                    if (choice==3)
                        FundTransfer(TransferData);
                
                    System.out.println("Enter -1 to stop, or 0 if you would like to perform another function");
                    choice = in.nextInt();
                }while(choice != -1);
        }
        
        //Balance inquiry function
        public void BalanceInquiry(){
            System.out.println("Your current balance is: " + Funds);
        }
        
        //Cash withdraw function
        public void CashWithdrawal(){
            float Withdraw;
            System.out.println("How much would you like to withdraw?");
            Withdraw= in.nextFloat();
            
            if(Withdraw > Funds)
                System.out.println("Invalid request, you don't have the necessary funds!");
            else
            {
                Funds= Funds - Withdraw;
                System.out.println("You have withdrawn: $" + Withdraw);
                System.out.println("Your new balance is: $" + Funds);
            }
        }
        
        //Fund transfer function
        public void FundTransfer(int []TransferData){
            
            //Sending transfer details back to main class
            System.out.println("How much would you like to transfer?");
            TransferData[0] = in.nextInt();
            System.out.println("Enter user number whom you would like to transfer to: ");
            TransferData[1]= in.nextInt();
            
            if(TransferData[0] > Funds)
                System.out.println("Invalid request, you don't have the necessary funds!");
            else{
                Funds = Funds - TransferData[0];
                System.out.println("Funds transferred!");
                System.out.println("Your new balance is: $" + Funds);
            }
        }
        
        //Fund Adding function (in case of transfer)
        public void AddFunds(int Transfer){
            Funds = Funds + Transfer;
        }
    }