/*
    Olivia Papotto
    Phillip Do
 */
package cpsc2150.mortgages;

/**
 * @invariant 0 <= cost
 * @invariant 0 <= downPayment
 * @invariant 0 <= years
 * @invariant
 *
 * Correspondence
 */
public class Mortgage extends AbsMortgage implements IMortgage{

    private double cost;
    private double downPayment;
    private int years;
    private ICustomer customer;
    private double DebtToIncomeRatio;
    private double payment;
    private double rate;

    /**
     *
     * @param c is cost of home
     * @param dp is the down payment
     * @param y is the number of years
     * @param ic is the customer object
     * @post Mortage(c, dp, y, ic)
     */
    Mortgage(double c, double dp, int y, ICustomer ic){
        cost = c;
        downPayment = dp;
        years = y;
        customer = ic;

        rate = calc_rate();
        payment = calc_payment();

        //Damion said 12 is okay as a "magic number" in this case
        DebtToIncomeRatio = ((customer.getMonthlyDebtPayments()+payment))/(customer.getIncome()/12);
    }

    /**
     *
     * @return
     */
    public boolean loanApproved(){
        boolean approved = true;

        if(getRate() >= RATETOOHIGH || (downPayment/cost) < MIN_PERCENT_DOWN || DebtToIncomeRatio > DTOITOOHIGH){
            approved = false;
        }

        return approved;
    }

    /**
     *
     * @return
     */
    public double getPayment(){
        return payment;
    }

    /**
     *
     * @return
     */
    public double getRate(){
        return rate;
    }

    /**
     *
     * @return
     */
    public double getPrincipal(){
        return cost - downPayment;
    }

    /**
     *
     * @return
     */
    public int getYears(){
        return years;
    }

    /**
     *
     * @return
     */
    private double calc_payment(){
        double numerator = ((rate/12)*getPrincipal());
        double denominator = 1 - Math.pow((1 + (rate/12)),-(years*12));

        return numerator / denominator;
    }

    /**
     *
     * @return
     */
    private double calc_rate(){
        double APR = BASERATE;

        if(years < MAX_YEARS){
            APR += GOODRATEADD;
        }else{
            APR += NORMALRATEADD;
        }

        if((downPayment/cost) < PREFERRED_PERCENT_DOWN){
            APR += GOODRATEADD;
        }

        if(customer.getCreditScore() < BADCREDIT){
            APR += VERYBADRATEADD;
        }else if(customer.getCreditScore() >= BADCREDIT && customer.getCreditScore() < FAIRCREDIT){
            APR += BADRATEADD;
        }else if(customer.getCreditScore() >= FAIRCREDIT && customer.getCreditScore() < GOODCREDIT){
            APR += NORMALRATEADD;
        }else if(customer.getCreditScore() >= GOODCREDIT && customer.getCreditScore() < GREATCREDIT){
            APR += GOODRATEADD;
        }

        return APR;
    }

}
