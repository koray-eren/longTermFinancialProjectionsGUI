package sample;

public class IncomeInput extends CashflowInput{
    private boolean isTaxable = true;

    public IncomeInput(String name, double amount, int startYear, int endYear, int frequency, boolean isTaxable, double indexation) {
        super(name, amount, startYear, endYear, frequency, indexation);
        this.isTaxable = isTaxable;
    }

    public IncomeInput(String name, double indexation) {
        super(name, indexation);
    }

    public boolean getIsTaxable() {
        return isTaxable;
    }

    public void setTaxable(boolean taxable) {
        isTaxable = taxable;
    }

}
