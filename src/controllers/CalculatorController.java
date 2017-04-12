package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML private TextField display;

    private final int limit;
    private int current_limit;
    private String operator;
    private double result;
    private double value;
    private boolean isReset;

    public CalculatorController() {
        limit = 15;
        current_limit = 0;
        isReset = false;
    }

    @FXML
    public void handleDigit(ActionEvent event) {
        if(!isReset)
            resetDisplay();
        String text = ((Button) event.getSource()).getText();
        if(current_limit == 0) {
            setOutput(text);
            current_limit++;
        }
        else if(current_limit < limit) {
            updateOutput(text);
            current_limit++;
        }
    }

    @FXML
    public void handleOperator(ActionEvent event) {
        if(!isOperator()) {
            result = Double.parseDouble(display.getText());
            operator = ((Button) event.getSource()).getText();
            isReset = false;
        } else if(isOperator()) {
            value = Double.parseDouble(display.getText());
            calculate(value, operator);
            display.setText(""+result);
            operator = ((Button) event.getSource()).getText();
            isReset = false;
        }
    }

    @FXML
    public void handleEquals() {
        if(isOperator()) {
            value = Double.parseDouble(display.getText());
            calculate(value, operator);
            display.setText(""+result);
            operator = null;
            isReset = false;
        }
    }

    @FXML
    public void handleZero(ActionEvent event) {
        if(!isReset)
            resetDisplay();
        String text = ((Button) event.getSource()).getText();
        if(current_limit > 0 && current_limit < limit) {
            updateOutput(text);
            current_limit++;
        }
    }

    @FXML
    public void handleSeparator(ActionEvent event) {
        if(!isReset)
            resetDisplay();
        String text = ((Button) event.getSource()).getText();
        if(current_limit == 0) {
            //setOutput("0"+text);
            //current_limit = 2;
            updateOutput(".");
            current_limit++;
        }
        else if(current_limit < limit && !isSeparator()) {
            updateOutput(text);
            current_limit++;
        }
    }

    @FXML
    public void handleUndo() {
        if(!isReset)
            resetDisplay();
        //if(!isOperator()) {
            if (current_limit > 0) {
                String output = display.getText();
                output = output.substring(0, output.length() - 1);
                current_limit--;
                if (current_limit == 0)
                    setOutput("0");
                else
                    setOutput(output);
            }
        //}
    }

    @FXML
    public void handleNegate() {
        if(!isOperator()) {
            //resetDisplay();
            String output = display.getText();
            if (current_limit > 0 && !isNegate())
                setOutput("-" + output);
            else if (current_limit > 0 && isNegate())
                setOutput(output.substring(1));
        }
    }

    @FXML
    public void handleReset() {
        display.setText("0");
        current_limit = 0;
        operator = null;
    }


    ///////////////////////////////////////////////////////////////////////////////
    private void updateOutput(String update) {
        String oldOutput = display.getText();
        String newOutput = oldOutput + update;
        display.setText(newOutput);
    }

    private void setOutput(String output) {
        display.setText(output);
    }

    private boolean isSeparator() {
        String output = display.getText();
        if(output.contains("."))
            return true;
        else
            return false;
    }

    private boolean isNegate() {
        String output = display.getText();
        if(output.contains("-"))
            return true;
        else
            return false;
    }

    private boolean isOperator() {
        if(operator != null)
            return true;
        else
            return false;
    }

    private void resetDisplay() {
        current_limit = 0;
        display.setText("0");
        isReset = true;
        //operator = null;
    }

    private void calculate(double value, String operator) {
        switch (operator) {
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

}
