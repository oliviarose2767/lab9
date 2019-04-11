package cpsc2150.mortgages;

import java.util.*;

public class MortgageView implements IMortgageView{

    Scanner scanner = new Scanner(System.in);
    private IMortgageController obj;

    @Override
    public void setController(IMortgageController c) {
        obj = c;
    }

    @Override
    public double getHouseCost() {
        System.out.println("How much does the house cost?");
        double cost = scanner.nextDouble();
        return cost;
    }

    @Override
    public double getDownPayment() {
        System.out.println("How much is the down payment?");
        double dp = scanner.nextDouble();
        return dp;
    }

    @Override
    public int getYears() {
        System.out.println("How many years?");
        int years = scanner.nextInt();
        return years;
    }

    @Override
    public double getMonthlyDebt() {
        System.out.println("How much are your monthly debt payments?");
        double monthly = scanner.nextDouble();
        return monthly;
    }

    @Override
    public double getYearlyIncome() {
        System.out.println("How much is your yearly income?");
        double income = scanner.nextDouble();
        return income;
    }

    @Override
    public int getCreditScore() {
        System.out.println("What is your credit score?");
        int score = scanner.nextInt();
        return score;
    }

    @Override
    public String getName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your name?");
        String s = scanner.nextLine();
        return s;
    }

    @Override
    public void printToUser(String s) {
        System.out.println(s);
    }

    /*
     * not needed
     */
    @Override
    public void displayPayment(double p) {

    }

    /*
     * not needed
     */
    @Override
    public void displayRate(double r) {

    }

    /*
     * not needed
     */
    @Override
    public void displayApproved(boolean a) {

    }

    @Override
    public boolean getAnotherMortgage() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Would you like to apply for another mortgage? Y/N");
        char again = reader.next().charAt(0);
        if(again == 'y' || again == 'Y'){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean getAnotherCustomer() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Would you like to consider another customer? Y/N");
        char again = reader.next().charAt(0);
        if(again == 'y' || again == 'Y'){
            return true;
        }else {
            return false;
        }
    }
}
