package cpsc2150.mortgages;

/*
 * this is where validation happens
 */
public class MortgageController implements IMortgageController {

    private IMortgageView obj;

    public MortgageController(IMortgageView view) {
        obj = view;
    }

    /**
     * This will handle the processing of a mortgage application
     * @pre: none
     * @post: none
     */
    public void submitApplication(){
        String incomeError = "Income must be greater than 0";
        String monthlyError = "Debt must be greater than or equal to 0.";
        String creditError = "Credit Score must be greater than 0 and less than 850";
        String costError = "Cost must be greater than 0.";
        String dpError = "Down Payment must be greater than 0 and less than the cost of the house.";
        String yearsError = "Years must be greater than 0.";

        do{
            String name = obj.getName();

            double income = 0;
            do {
                income = obj.getYearlyIncome();
                if(income < 0){
                    obj.printToUser(incomeError);
                }
            } while (income < 0);

            double debt = 0;
            do {
                debt = obj.getMonthlyDebt();
                if(debt < 0){
                    obj.printToUser(monthlyError);
                }
            } while (debt < 0);

            int score = 0;
            do {
                score = obj.getCreditScore();
                if(score < 0 || score > 850){
                    obj.printToUser(creditError);
                }
            } while (score < 0 || score > 850);

            do {
                double cost = 0;
                do {
                    cost = obj.getHouseCost();
                    if(cost < 0){
                        obj.printToUser(costError);
                    }
                } while (cost < 0);

                double dp = 0;
                do {
                    dp = obj.getDownPayment();
                    if(dp < 0 || dp > cost){
                        obj.printToUser(dpError);
                    }
                } while (dp < 0 || dp > cost);

                int years = 0;
                    do {
                        years = obj.getYears();
                        if(years < 0){
                            obj.printToUser(yearsError);
                        }
                    } while (years < 0);

                    obj.printToUser("Name: " + name);
                    obj.printToUser("Income: " + income);
                    obj.printToUser("Credit Score: " + score);
                    obj.printToUser("Monthly Debt: " + debt);

                    ICustomer customer = new Customer(debt, income, score, name);
                    Mortgage temp = new Mortgage(cost, dp, years, customer);

                    obj.printToUser("Mortgage info: ");
                    obj.printToUser("Principal Amount: $" + temp.getPrincipal());
                    double ir = temp.getRate() * 100;
                    obj.printToUser("Interest Rate: " + ir + "%");
                    obj.printToUser("Term: " + temp.getYears() + " years");
                    obj.printToUser("Monthly Payment " + temp.getPayment() + "\n");

            }while(obj.getAnotherMortgage());
        }while(obj.getAnotherCustomer());
    }

}
