package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

public class InputsController implements Initializable {

    private int years = 5;
    private static double indexation = 0.025;

    private final double TAX_RATE = 0.3;

    // indices for items in cashflowSummary table - this table will always only these 3 items, with a potential
    // fourth to be added later to show net cashflow allocation
    private final int TOTAL_INCOME_INDEX = 0;
    private final int TOTAL_EXPENSES_INDEX = 1;
    private final int NET_CASHFLOW_INDEX = 2;

    // always present items in incomeOutputs table
    private int ASSET_INCOME_INDEX = 0;
    // incomeExpenses table
    private int ASSET_EXPENSE_INDEX = 0;
    private int LIABILITY_EXPENSE_INDEX = 1;
    private int INCOME_TAX_INDEX = 2;

    @FXML
    private TableView assumptionsTable;
    @FXML
    private TableView incomeInputs;
    @FXML
    private TableView expenseInputs;
    @FXML
    private TableView expenseOutputs;
    @FXML
    private TableView cashflowSummary;
    @FXML
    private TableView incomeOutputs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addInputColumns();

        addCashflowOutputColumns(incomeOutputs);
        addCashflowOutputColumns(expenseOutputs);
        addCashflowOutputColumns(cashflowSummary);

        assumptionsTable.getItems().add(new AssumptionInput(indexation, years) );

        addCashflowSummaryItems();

        addStandardOutputRows();

        CashflowInput testIncome = new IncomeInput("test income", 1000, 1, years, 1,true, indexation);
        incomeOutputs.getItems().add(testIncome);
        incomeInputs.getItems().add(testIncome);

        try {
            updateCashflowSummary();
            calculateTax();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getIndexation() {
        return indexation;
    }

    public void addInputColumns(){
        addAssumptionColumns();

        addCashflowInputColumns(incomeInputs);
        addIncomeColumns();

        addCashflowInputColumns(expenseInputs);
        addExpenseColumns();
    }

    public void addAssumptionColumns(){
        assumptionsTable.setEditable(true);
        assumptionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn indexationColumn = new TableColumn<AssumptionInput, Double>("Indexation (CPI)");
        indexationColumn.setCellValueFactory(new PropertyValueFactory<AssumptionInput, Double>("indexation") );
        assumptionsTable.getColumns().add(indexationColumn);
        indexationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() ) );
        indexationColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AssumptionInput, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AssumptionInput, Double> event) {
                AssumptionInput assumption = event.getRowValue();
                assumption.setIndexation(event.getNewValue() );
                indexation = event.getNewValue();
                for (int i = 0; i < incomeOutputs.getItems().size(); i++) {
                    CashflowInput temp = (CashflowInput) incomeOutputs.getItems().get(i);
                    temp.updateData();
                }
                for (int i = 0; i < expenseOutputs.getItems().size(); i++) {
                    CashflowInput temp = (CashflowInput) expenseOutputs.getItems().get(i);
                    temp.updateData();
                }

                try {
                    updateCashflowSummary();
                    refreshStandardOutputRows();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                incomeOutputs.refresh();
                expenseOutputs.refresh();

            }
        });

        TableColumn projectionLengthColumn = new TableColumn<AssumptionInput, Integer>("Projection Length (1-100 Years)");
        projectionLengthColumn.setCellValueFactory(new PropertyValueFactory<AssumptionInput, Integer>("projectionLength") );
        assumptionsTable.getColumns().add(projectionLengthColumn);
        projectionLengthColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() ) );
        projectionLengthColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AssumptionInput, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<AssumptionInput, Integer> event) {
                AssumptionInput assumption = event.getRowValue();
                assumption.setProjectionLength(event.getNewValue() );

                refreshCashflowOutput(event.getNewValue(), incomeOutputs );
                refreshCashflowOutput(event.getNewValue(), expenseOutputs );
                refreshCashflowOutput(event.getNewValue(), cashflowSummary );

                for (int i = 0; i < incomeInputs.getItems().size(); i++) {
                    IncomeInput temp = (IncomeInput) incomeInputs.getItems().get(i);
                    incomeOutputs.getItems().add(temp);
                }
                for (int i = 0; i < expenseInputs.getItems().size(); i++) {
                    ExpenseInput temp = (ExpenseInput) expenseInputs.getItems().get(i);
                    expenseOutputs.getItems().add(temp);
                }

                addCashflowSummaryItems();

                try {
                    addStandardOutputRows();
                    refreshStandardOutputRows();
                    updateCashflowSummary();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                incomeOutputs.refresh();
                expenseOutputs.refresh();
            }
        });
    }

    public void addCashflowInputColumns(TableView table){
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-alignment: CENTER;");
        TableColumn nameColumn = new TableColumn<CashflowInput, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<CashflowInput, String>("name") );
        table.getColumns().add(nameColumn);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn() );
        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashflowInput, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CashflowInput, String> event) {
                CashflowInput input = event.getRowValue();
                input.setName(event.getNewValue() );
                input.updateData();

                try {
                    updateCashflowSummary();
                    refreshStandardOutputRows();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                incomeOutputs.refresh();
                expenseOutputs.refresh();
            }
        });

        TableColumn amountColumn = new TableColumn<CashflowInput, Double>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<CashflowInput, Double>("amount") );
        table.getColumns().add(amountColumn);
        amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() ) );
        amountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashflowInput, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CashflowInput, Double> event) {
                CashflowInput input = event.getRowValue();
                input.setAmount(event.getNewValue() );
                input.updateData();
                try {
                    refreshStandardOutputRows();
                    updateCashflowSummary();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                incomeOutputs.refresh();
                expenseOutputs.refresh();
            }
        });

        TableColumn startYearColumn = new TableColumn<CashflowInput, Integer>("Start Year");
        startYearColumn.setCellValueFactory(new PropertyValueFactory<CashflowInput, Integer>("startYear") );
        table.getColumns().add(startYearColumn);
        startYearColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() ) );
        startYearColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashflowInput, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CashflowInput, Integer> event) {
                CashflowInput input = event.getRowValue();
                input.setStartYear(event.getNewValue() );
                input.updateData();
                try {
                    refreshStandardOutputRows();
                    updateCashflowSummary();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                incomeOutputs.refresh();
                expenseOutputs.refresh();
            }
        });

        TableColumn endYearColumn = new TableColumn<CashflowInput, Integer>("End Year");
        endYearColumn.setCellValueFactory(new PropertyValueFactory<CashflowInput, Integer>("endYear") );
        table.getColumns().add(endYearColumn);
        endYearColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() ) );
        endYearColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashflowInput, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CashflowInput, Integer> event) {
                CashflowInput input = event.getRowValue();
                input.setEndYear(event.getNewValue() );
                input.updateData();
                try {
                    refreshStandardOutputRows();
                    updateCashflowSummary();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                incomeOutputs.refresh();
                expenseOutputs.refresh();
            }
        });


        TableColumn frequencyColumn = new TableColumn<CashflowInput, Integer>("Frequency");
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<CashflowInput, Integer>("frequency") );
        table.getColumns().add(frequencyColumn);
        frequencyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() ) );
        frequencyColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CashflowInput, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CashflowInput, Integer> event) {
                CashflowInput input = event.getRowValue();
                input.setFrequency(event.getNewValue() );
                input.updateData();

                incomeOutputs.refresh();
                expenseOutputs.refresh();
                try {
                    refreshStandardOutputRows();
                    updateCashflowSummary();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addIncomeColumns() {
        TableColumn taxableColumn = new TableColumn<IncomeInput, Integer>("Taxable?");
        taxableColumn.setCellValueFactory(new PropertyValueFactory<IncomeInput, Integer>("isTaxable") );
        incomeInputs.getColumns().add(taxableColumn);
        taxableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter() ) );
        taxableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<IncomeInput, Boolean>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<IncomeInput, Boolean> event) {
                IncomeInput input = event.getRowValue();
                input.setTaxable(event.getNewValue() );
                input.updateData();
                incomeOutputs.refresh();
            }
        });

    }

    public void addExpenseColumns() {
        TableColumn deductibleColumn = new TableColumn<ExpenseInput, Integer>("Deductible?");
        deductibleColumn.setCellValueFactory(new PropertyValueFactory<ExpenseInput, Integer>("isDeductible") );
        expenseInputs.getColumns().add(deductibleColumn);
        deductibleColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter() ) );
        deductibleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ExpenseInput, Boolean>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ExpenseInput, Boolean> event) {
                ExpenseInput input = event.getRowValue();
                input.setDeductible(event.getNewValue() );
                input.updateData();
                incomeOutputs.refresh();
            }
        });
    }

    public void addCashflowOutputColumns(TableView table){
        TableColumn nameColumn = new TableColumn<CashflowInput, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<CashflowInput, String>("name") );
        table.getColumns().add(nameColumn);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn() );

        for (int i = 1; i < years + 1; i++) {
            TableColumn newColumn = new TableColumn<CashflowInput, Double>("Year "+i);
            newColumn.setCellValueFactory(new PropertyValueFactory<CashflowInput, Double>("year"+i) );
            table.getColumns().add(newColumn);
            newColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() ) );
        }
    }


    public void addIncome(ActionEvent e) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        IncomeInput incomeInput = new IncomeInput("new income", indexation);
        incomeInputs.getItems().add(incomeInput);
        incomeOutputs.getItems().add(incomeInput);
        updateCashflowSummary();

        for (int i = 0; i < incomeOutputs.getItems().size(); i++) {
            if (incomeOutputs.getItems().get(i) instanceof IncomeInput){
                IncomeInput temp = (IncomeInput) incomeOutputs.getItems().get(i);
                double taxableIncome = 0;
                if (temp.getIsTaxable() ){
                    for (int j = temp.getStartYear() + 1; j < temp.getEndYear() + 1; j++) {
                        try {
                            Method getterMethod = temp.getClass().getMethod("getYear"+j);
                            Double tempAmount = (Double) getterMethod.invoke(temp);
                            taxableIncome += tempAmount;
                        } catch (Exception exception){
                            exception.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    public void deleteIncome(ActionEvent e) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        CashflowInput temp = (CashflowInput) incomeInputs.getSelectionModel().getSelectedItem();
        incomeInputs.getItems().remove(temp );
        incomeOutputs.getItems().remove(temp );
        updateCashflowSummary();
    }

    public void addExpense(ActionEvent e) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ExpenseInput expenseInput = new ExpenseInput("new expense", indexation);
        expenseInputs.getItems().add(expenseInput);
        expenseOutputs.getItems().add(expenseInput);
        updateCashflowSummary();
    }

    public void deleteExpense(ActionEvent e) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        CashflowInput temp = (CashflowInput) expenseInputs.getSelectionModel().getSelectedItem();
        expenseInputs.getItems().remove(temp );
        expenseOutputs.getItems().remove(temp );
        updateCashflowSummary();
    }


    public void addCashflowSummaryItems(){
        CashflowInput totalIncome = new ExpenseInput("TOTAL INCOME", 0, 1, 100, 1,false, indexation);
        cashflowSummary.getItems().add(TOTAL_INCOME_INDEX, totalIncome);

        CashflowInput totalExpenses = new ExpenseInput("TOTAL EXPENSES", 0, 1, 100, 1,false,indexation);
        cashflowSummary.getItems().add(TOTAL_EXPENSES_INDEX, totalExpenses);

        CashflowInput netCashflow = new ExpenseInput("NET CASHFLOW", 0, 1, 100, 1,false,indexation);
        cashflowSummary.getItems().add(NET_CASHFLOW_INDEX, netCashflow);
    }

    public void refreshCashflowOutput(Integer newValue, TableView table){
        table.getItems().clear();
        for (int i = table.getColumns().size(); i > 0; i--) {
            table.getColumns().remove(i - 1);
        }
        years = newValue;
        addCashflowOutputColumns(table);
    }

    public void updateTotalIncome() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < years; i++) {
            for (int j = 0; j < incomeInputs.getItems().size(); j++) {
                IncomeInput incomeInput = (IncomeInput) incomeInputs.getItems().get(j);

                Method getterMethod = incomeInput.getClass().getMethod("getYear"+ (i + 1) );
                Double tempAmount = (Double) getterMethod.invoke(incomeInput);

                ExpenseInput incomeData = (ExpenseInput) cashflowSummary.getItems().get(0);

                Method getterMethod2 = incomeData.getClass().getMethod("getYear"+ (i + 1) );
                Double tempAmount2 = (Double) getterMethod2.invoke(incomeData);

                double total = Math.round( (tempAmount + tempAmount2) * 100d) / 100d;
                Method setterMethod = incomeData.getClass().getMethod("setYear"+ (i + 1), new Class[] { double.class } );
                setterMethod.invoke(incomeData, new Object[] { total } );
            }
        }
    }

    public void updateTotalExpenses() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < years; i++) {
            for (int j = 0; j < expenseInputs.getItems().size(); j++) {
                ExpenseInput expenseInput = (ExpenseInput) expenseInputs.getItems().get(j);

                Method getterMethod = expenseInput.getClass().getMethod("getYear"+ (i + 1) );
                Double tempAmount = (Double) getterMethod.invoke(expenseInput);

                ExpenseInput incomeData = (ExpenseInput) cashflowSummary.getItems().get(1);

                Method getterMethod2 = incomeData.getClass().getMethod("getYear"+ (i + 1) );
                Double tempAmount2 = (Double) getterMethod2.invoke(incomeData);

                double total = Math.round( (tempAmount + tempAmount2) * 100d) / 100d;
                Method setterMethod = incomeData.getClass().getMethod("setYear"+ (i + 1), new Class[] { double.class } );
                setterMethod.invoke(incomeData, new Object[] { total } );
            }
        }
    }

    public void updateNetCashflow() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < years; i++) {
            ExpenseInput totalIncome = (ExpenseInput) cashflowSummary.getItems().get(TOTAL_INCOME_INDEX);
            Method getTotalIncomeYearI = totalIncome.getClass().getMethod("getYear"+ (i + 1) );
            Double totalIncomeYearI = (Double) getTotalIncomeYearI.invoke(totalIncome);

            ExpenseInput totalExpenses = (ExpenseInput) cashflowSummary.getItems().get(TOTAL_EXPENSES_INDEX);
            Method getTotalExpensesYearI = totalExpenses.getClass().getMethod("getYear"+ (i + 1) );
            Double totalExpensesYearI = (Double) getTotalExpensesYearI.invoke(totalExpenses);

            double netAmount = Math.round( (totalIncomeYearI - totalExpensesYearI) * 100d) / 100d;
            ExpenseInput netCashflow = (ExpenseInput) cashflowSummary.getItems().get(NET_CASHFLOW_INDEX);
            Method setNetCashflowYearI = netCashflow.getClass().getMethod("setYear"+ (i + 1), new Class[] { double.class } );
            setNetCashflowYearI.invoke(netCashflow, new Object[] { netAmount } );
        }
    }

    public double getYearData(CashflowInput input, int i) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getAmountYearI = input.getClass().getMethod("getYear"+ (i + 1) );
        return (Double) getAmountYearI.invoke(input);
    }

    public void clearCashflowSummary() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (int x = 0; x < 3; x++) {
            for (int i = 0; i < years; i++) {
                for (int j = 0; j < incomeInputs.getItems().size(); j++) {
                    ExpenseInput incomeData = (ExpenseInput) cashflowSummary.getItems().get(x);
                    Method setterMethod = incomeData.getClass().getMethod("setYear"+ (i + 1), new Class[] { double.class } );
                    setterMethod.invoke(incomeData, new Object[] { 0 } );
                }
            }
        }
    }

    public void updateCashflowSummary() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        clearCashflowSummary();
        try {
            updateTotalIncome();
            updateTotalExpenses();
            updateNetCashflow();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        cashflowSummary.refresh();
    }

    public void calculateTax() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < years; i++) {
            double taxAmount = 0;
            for (int j = 0; j < incomeInputs.getItems().size(); j++) {
                IncomeInput income = (IncomeInput) incomeInputs.getItems().get(j);
                if( income.getIsTaxable() ){
                    taxAmount += (getYearData(income, i) * TAX_RATE);
                }
            }
            for (int j = 0; j < expenseInputs.getItems().size(); j++) {
                ExpenseInput expense = (ExpenseInput) expenseInputs.getItems().get(j);
                if( expense.getIsDeductible() ){
                    taxAmount -= (getYearData(expense, i) * TAX_RATE);
                }
            }
            ExpenseInput incomeTax = (ExpenseInput) expenseOutputs.getItems().get(INCOME_TAX_INDEX);
            Method setterMethod = incomeTax.getClass().getMethod("setYear"+ (i + 1), new Class[] { double.class } );
            taxAmount = Math.round( (taxAmount) * 100d) / 100d;
            setterMethod.invoke(incomeTax, new Object[] { taxAmount } );
        }
    }
    
    public void addStandardOutputRows(){
        incomeOutputs.getItems().add(ASSET_INCOME_INDEX, new IncomeInput("Asset Income", 0, 1, years, 1,true, indexation) );
        // add standard line items: asset income, asset expenses, liability interest, tax
        expenseOutputs.getItems().add(ASSET_EXPENSE_INDEX, new ExpenseInput("Asset Expenses", 0, 1, years, 1,false, indexation) );
        expenseOutputs.getItems().add(LIABILITY_EXPENSE_INDEX, new ExpenseInput("Liability Interest", 0, 1, years, 1,false, indexation) );
        expenseOutputs.getItems().add(INCOME_TAX_INDEX, new ExpenseInput("Income Tax", 0, 1, years, 1,false, indexation) );
    }

    public void refreshStandardOutputRows() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        calculateTax();
    }

}