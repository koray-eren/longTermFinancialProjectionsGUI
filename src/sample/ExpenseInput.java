package sample;

public class ExpenseInput extends CashflowInput{
    private boolean isDeductible;

    public ExpenseInput(String name, double amount, int startYear, int endYear, int frequency, boolean isDeductible, double indexation) {
        super(name, amount, startYear, endYear, frequency, indexation);
        this.isDeductible = isDeductible;
    }

    public ExpenseInput(String name, double indexation) {
        super(name, indexation);
    }

    public boolean getIsDeductible() {
        return isDeductible;
    }

    public void setDeductible(boolean deductible) {
        isDeductible = deductible;
    }

}
