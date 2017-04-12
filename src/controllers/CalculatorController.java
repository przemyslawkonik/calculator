package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML private TextField output;

    private boolean clear;
    private boolean isSeparator;
    private boolean isNegate;

    public CalculatorController() {
        clear = true;
        isSeparator = false;
        isNegate = false;
    }

    @FXML
    public void handleDigit(ActionEvent event) {
        if(clear) {
            output.setText("");
            clear = false;
        }
        String digit = ((Button) event.getSource()).getText();
        updateOutput(digit);
    }

    @FXML
    public void handleOperator(ActionEvent event) {

    }

    @FXML
    public void handleEquals() {

    }

    @FXML
    public void handleZero() {
        String text = output.getText();
        if(!text.equals("0")) {
            updateOutput("0");
        }
    }

    @FXML
    public void handleSeparator() {
        if(!isSeparator)
            updateOutput(".");
        isSeparator = true;
        clear = false;
    }

    @FXML
    public void handleUndo() {
        String outputText = output.getText();
        if(outputText.length() == 1) {
            output.setText("0");
            clear = true;
        }
        else {
            if(outputText.endsWith("."))
                isSeparator = false;
            outputText = outputText.substring(0, outputText.length() - 1);
            output.setText(outputText);
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
    }


    public void updateOutput(String text) {
        String oldOutput = output.getText();
        String newOutput = oldOutput + text;
        output.setText(newOutput);
    }

}
