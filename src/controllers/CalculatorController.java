package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML private TextField output;

    //private String oldOutput;
    //private String currentDigit;
    private String currentOperator;
    private double result;
    private double currentValue;

    private boolean isResultEmpty;
    private boolean isOperatorEmpty;
    private boolean isOutput;

    private final int displayLimit;
    private int currentLimit;

    public CalculatorController() {
        result = 0;
        isResultEmpty = true;
        isOperatorEmpty = true;
        isOutput = false;
        displayLimit = 15;
        currentLimit = 0;
    }

    @FXML
    public void handleDigit(ActionEvent event) {
        if(!isOperatorEmpty) {
            output.setText("");
            //isOperatorEmpty = true;
            //System.out.println(isOperatorEmpty);
        }
        if(currentLimit < displayLimit) {
            String oldOutput = output.getText();
            String currentDigit = ((Button) event.getSource()).getText();
            output.setText(oldOutput + currentDigit);
            isOutput = true;
            currentLimit++;
            System.out.println(currentLimit);
        }
    }

    @FXML
    public void handleOperator(ActionEvent event) {
        if(isOutput) {
            currentValue = Double.parseDouble(output.getText());

            if (!isOperatorEmpty) {
                calculate();
                output.setText("" + result);
            }

            if (isResultEmpty) {
                result = currentValue;
                isResultEmpty = false;
            }
            isOperatorEmpty = false;
            currentOperator = ((Button) event.getSource()).getText();
        }
        currentLimit = 0;
        isOutput = false;
    }

    @FXML
    public void handleEquals() {
        if(!isOperatorEmpty) {
            currentValue = Double.parseDouble(output.getText());
            calculate();
            output.setText("" + result);
            isOperatorEmpty = true;
        }
    }

    @FXML
    public void handleReset() {
        result = 0;
        currentLimit = 0;
        isResultEmpty = true;
        isOperatorEmpty = true;
        isOutput = false;
        output.clear();
    }


    private void calculate() {
        switch(currentOperator) {
            case "+": {
                result += currentValue;
                break;
            }
            case "-": {
                result -= currentValue;
                break;
            }
            case "*": {
                result *= currentValue;
                break;
            }
            case "/": {
                result /= currentValue;
                break;
            }
        }
    }
}
