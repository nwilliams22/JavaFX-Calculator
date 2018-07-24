/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fxcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @author Nick Williams
 */
public class Controller {
    
    @FXML
    private Text output;
    private double number1 = 0;
    private String operator = "";
    private boolean start = true;
    
    
    private final Model model = new Model();
    
    @FXML
    private void processNumberpad(ActionEvent event){
        
        // sets output to empty if program has just started
        if(start){
            output.setText("");
            start = false;
        }
        
        // sets the output to the number that was clicked
        String number = ((Button)event.getSource()).getText();
        output.setText(output.getText() + number);
        
        // clears output
        if(number.equals("Clear Entry")){
            output.setText("");
        }
        
        // sets output to plus/minus
//        double plusMinus = Double.parseDouble(String.valueOf(output.getText()));
//        plusMinus = plusMinus * (-1);
//        output.setText(String.valueOf(plusMinus));

        if("+-".equals(operator)){
            double plusMinus = Double.parseDouble(String.valueOf(output.getText()));
            plusMinus = plusMinus * -1;
            output.setText(String.valueOf(plusMinus));
        }
        
        /*
        I'm not sure what I'm doing wrong on the +- operator
        I'm assuming it's something simple that I'm not noticing.
        I tried putting it in the processOperators function, but it doesn't work at all,
        Rather than the processNumberpad function where it at least puts something on the output
        
        I'm probably over-thinking it or something :(
        */
        
    }
    
    @FXML
    private void processOperators(ActionEvent event){
        String operatorText = ((Button)event.getSource()).getText();
        
        if(!"=".equals(operatorText)){
            if(!operator.isEmpty())
                return;
            
            operator = operatorText;
            number1 = Double.parseDouble(output.getText());
            output.setText("");
        }else{
            if(operator.isEmpty())
                return;
            
            output.setText(String.valueOf(model.calculate(number1, Double.parseDouble(output.getText()), operator)));
            operator = "";
            start = true;
        }
        
//        if("+-".equals(operator)){
//            double plusMinus = Double.parseDouble(String.valueOf(output.getText()));
//            plusMinus = plusMinus * -1;
//            output.setText(String.valueOf(plusMinus));
//        }
    }
}


