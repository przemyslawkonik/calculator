package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML private TextField output;
    @FXML private Button digit1;

    private boolean clear;
    private boolean isSeparator;
    private boolean isNegate;
    private boolean isOperator;
    private boolean isResult;
    private boolean isEqual;

    private double result;
    private double value;
    private String operator;
    private final int limit;
    private int currentLimit;

    public CalculatorController() {
        clear = true;
        isSeparator = false;
        isNegate = false;
        isOperator = false;
        isResult = false;
        isEqual = false;
        limit = 15;
        currentLimit = 0;
    }

    @FXML
    public void handleDigit(ActionEvent event) {
        if(isEqual) {
            output.setText("");
            clear = false;
            isSeparator = false;
            isNegate = false;
            isOperator = false;
            isResult = false;
            isEqual = false;
            currentLimit = 0;

        }
        else if(clear) {
            output.setText("");
            clear = false;
            isSeparator = false;
            currentLimit = 0;
        }
        if(currentLimit < limit) {
            String digit = ((Button) event.getSource()).getText();
            updateOutput(digit);
            currentLimit++;
        }
    }

    @FXML
    public void handleOperator(ActionEvent event) {
        if(!isOperator && !isResult) {
            value = Double.parseDouble(output.getText());
            if(isNegate)
                isNegate = false;
            result = value;
            operator = ((Button) event.getSource()).getText();
            isResult = true;
            isOperator = true;
            clear = true;
        }
        else if(isOperator && clear) {
            operator = ((Button) event.getSource()).getText();
        }
        else if(isOperator && !clear) {
            value = Double.parseDouble(output.getText());
            if(isNegate)
                isNegate = false;
            calculate();
            operator = ((Button) event.getSource()).getText();
            output.setText(""+result);
            clear = true;
        }
    }

    @FXML
    public void handleEquals() {
        if(isOperator && !clear) {
            value = Double.parseDouble(output.getText());
            calculate();
            output.setText(""+result);
            clear = true;
            isEqual = true;
            //isOperator = false;
        }
    }

    @FXML
    public void handleZero() {
        if(isEqual) {
            output.setText("");
            clear = false;
            isSeparator = false;
            isNegate = false;
            isOperator = false;
            isResult = false;
            isEqual = false;
            currentLimit = 0;

        }
        String text = output.getText();
        if(!text.equals("0") && currentLimit < limit) {
            updateOutput("0");
            currentLimit++;
        }
    }

    @FXML
    public void handleSeparator() {
        if(isEqual) {
            output.setText("");
            clear = false;
            isSeparator = false;
            isNegate = false;
            isOperator = false;
            isResult = false;
            isEqual = false;
            currentLimit = 0;

        }
        if(!isSeparator && currentLimit < limit) {
            updateOutput(".");
            isSeparator = true;
            clear = false;
            currentLimit++;
        }
    }

    @FXML
    public void handleUndo() {
        String outputText = output.getText();
        if(outputText.length() == 1) {
            output.setText("0");
            clear = true;
            currentLimit = 0;
        }
        else if(outputText.length() == 2 && isNegate) {
            output.setText("0");
            clear = true;
            currentLimit = 0;
        }
        else {
            if(outputText.endsWith("."))
                isSeparator = false;
            outputText = outputText.substring(0, outputText.length() - 1);
            output.setText(outputText);
            currentLimit--;
        }

    }

    @FXML
    public void handleNegate() {
        String text = output.getText();
        if(!isNegate) {
            if (!text.equals("0") && !text.equals("0.")) {
                output.setText("-" + text);
                isNegate = true;
            }
        }
        else {
            output.setText(text.substring(1));
            isNegate = false;
        }
    }

    @FXML
    public void handleReset() {
        output.setText("0");
        clear = true;
        isSeparator = false;
        isNegate = false;
        isOperator = false;
        isResult = false;
        isEqual = false;
        currentLimit = 0;
    }


    public void updateOutput(String text) {
        String oldOutput = output.getText();
        String newOutput = oldOutput + text;
        output.setText(newOutput);
    }

    public void calculate() {
        switch(operator) {
            case "+": {
                result += value;
                break;
            }
            case "-": {
                result -= value;
                break;
            }
            case "*": {
                result *= value;
                break;
            }
            case "/": {
                result /= value;
                break;
            }
        }
    }

    private float round(double f, int places)

    {  float temp = (float)(f*(Math.pow(10, places)));

        temp = (Math.round(temp));

        temp = temp/(int)(Math.pow(10, places));

        return temp;
    }

}
