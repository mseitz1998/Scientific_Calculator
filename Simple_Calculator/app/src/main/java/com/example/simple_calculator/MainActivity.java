package com.example.simple_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //buttons for numbers zero-nine
    Button zeroButton, oneButton, twoButton, threeButton,
            fourButton, fiveButton, sixButton, sevenButton,
            eightButton, nineButton;

    //buttons to store and retrieve memory number, light/dark mode
    Button mButton, setMButton, lightDarkButton;

    //buttons for signs, equals, decimal, and operations
    Button signButton, addButton, subtractButton, multiplyButton,
            divideButton, equalsButton, decimalButton, clearButton,
            clearAllButton;

    //Buttons for trig
    Button sinButton, cosButton, tanButton, radDegButton, piButton;

    //text boxes for output
    TextView mEqualsOutput, degRadOutput, lightDarkOutput,
            equationHistoryOutput, userInputOutput;

    //scrollview
    ScrollView backgroundScroll;

    //program variables
    boolean lightDark = true; //light = true; dark = false
    boolean posNeg = true; //true = +, false = - , sign of user's current input
    boolean degRad = true; //true = deg, false = rad
    String inputHistory = ""; //holds the history of user's input operations
    String currentInput = ""; //current user value input
    double currentValue = 0; //holds the result of inputHistory operations as double
    double m = 1; //holds the value of stored m

    //enum and variable for current operation
    enum operation {none, add, subtract, divide, multiply, sin, cos, tan}
    operation currentOperation = operation.none; //holds current operation

    //keeping track of whether we need to input the first value,
    //an operator, or a value.
    enum cycleState{start, needOperator, needValue}
    cycleState currentState = cycleState.start;

    //decimal formatter, truncates to six decimal places
    DecimalFormat decFormatter = new DecimalFormat("#.######");

    //input checker
    boolean inputChecker(){
        if(currentInput.contains(".")){
            if(!(currentInput.indexOf('.') >= (currentInput.length() - 6))){
                //giving error message
                Toast.makeText(MainActivity.this , "Max 6 Decimal places", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else if(currentInput.equals("π")){//not allowing numbers with pi
            return false;
        }
        return true;//if no decimal or less than 6 decimal places
    }

    //light and dark mode methods
    void lightMode(){
        lightDark = true;//setting to light mode
        lightDarkOutput.setText("Light Mode");//setting color mode text

        //updating color for buttons
        lightDarkButton.setBackgroundColor(Color.BLUE);
        lightDarkButton.setTextColor(Color.BLACK);
        clearAllButton.setBackgroundColor(Color.BLUE);
        clearAllButton.setTextColor(Color.BLACK);
        clearButton.setBackgroundColor(Color.BLUE);
        clearButton.setTextColor(Color.BLACK);
        setMButton.setBackgroundColor(Color.BLUE);
        setMButton.setTextColor(Color.BLACK);
        equalsButton.setBackgroundColor(Color.BLUE);
        equalsButton.setTextColor(Color.BLACK);
        radDegButton.setBackgroundColor(Color.BLUE);
        radDegButton.setTextColor(Color.BLACK);
        sinButton.setBackgroundColor(Color.BLUE);
        sinButton.setTextColor(Color.BLACK);
        cosButton.setBackgroundColor(Color.BLUE);
        cosButton.setTextColor(Color.BLACK);
        tanButton.setBackgroundColor(Color.BLUE);
        tanButton.setTextColor(Color.BLACK);
        divideButton.setBackgroundColor(Color.BLUE);
        divideButton.setTextColor(Color.BLACK);
        multiplyButton.setBackgroundColor(Color.BLUE);
        multiplyButton.setTextColor(Color.BLACK);
        addButton.setBackgroundColor(Color.BLUE);
        addButton.setTextColor(Color.BLACK);
        subtractButton.setBackgroundColor(Color.BLUE);
        subtractButton.setTextColor(Color.BLACK);
        decimalButton.setBackgroundColor(Color.BLUE);
        decimalButton.setTextColor(Color.BLACK);
        mButton.setBackgroundColor(Color.BLUE);
        mButton.setTextColor(Color.BLACK);
        signButton.setBackgroundColor(Color.BLUE);
        signButton.setTextColor(Color.BLACK);
        zeroButton.setBackgroundColor(Color.BLUE);
        zeroButton.setTextColor(Color.BLACK);
        oneButton.setBackgroundColor(Color.BLUE);
        oneButton.setTextColor(Color.BLACK);
        twoButton.setBackgroundColor(Color.BLUE);
        twoButton.setTextColor(Color.BLACK);
        threeButton.setBackgroundColor(Color.BLUE);
        threeButton.setTextColor(Color.BLACK);
        fourButton.setBackgroundColor(Color.BLUE);
        fourButton.setTextColor(Color.BLACK);
        fiveButton.setBackgroundColor(Color.BLUE);
        fiveButton.setTextColor(Color.BLACK);
        sixButton.setBackgroundColor(Color.BLUE);
        sixButton.setTextColor(Color.BLACK);
        sevenButton.setBackgroundColor(Color.BLUE);
        sevenButton.setTextColor(Color.BLACK);
        eightButton.setBackgroundColor(Color.BLUE);
        eightButton.setTextColor(Color.BLACK);
        nineButton.setBackgroundColor(Color.BLUE);
        nineButton.setTextColor(Color.BLACK);
        piButton.setTextColor(Color.BLACK);
        piButton.setBackgroundColor(Color.BLUE);

        //updating text boxes
        mEqualsOutput.setTextColor(Color.BLACK);
        mEqualsOutput.setTextColor(Color.BLACK);
        mEqualsOutput.setBackgroundColor(Color.LTGRAY);
        degRadOutput.setTextColor(Color.BLACK);
        lightDarkOutput.setTextColor(Color.BLACK);
        equationHistoryOutput.setTextColor(Color.BLACK);
        userInputOutput.setTextColor(Color.BLACK);
        //scrollview update
        backgroundScroll.setBackgroundColor(Color.LTGRAY);
    }
    void darkMode(){
        lightDark = false;//setting to dark mode
        lightDarkOutput.setText("Dark Mode");//setting color mode text

        //updating color for buttons
        lightDarkButton.setBackgroundColor(Color.DKGRAY);
        lightDarkButton.setTextColor(Color.YELLOW);
        clearAllButton.setBackgroundColor(Color.DKGRAY);
        clearAllButton.setTextColor(Color.YELLOW);
        clearButton.setBackgroundColor(Color.DKGRAY);
        clearButton.setTextColor(Color.YELLOW);
        setMButton.setBackgroundColor(Color.DKGRAY);
        setMButton.setTextColor(Color.YELLOW);
        equalsButton.setBackgroundColor(Color.DKGRAY);
        equalsButton.setTextColor(Color.YELLOW);
        radDegButton.setBackgroundColor(Color.DKGRAY);
        radDegButton.setTextColor(Color.YELLOW);
        sinButton.setBackgroundColor(Color.DKGRAY);
        sinButton.setTextColor(Color.YELLOW);
        cosButton.setBackgroundColor(Color.DKGRAY);
        cosButton.setTextColor(Color.YELLOW);
        tanButton.setBackgroundColor(Color.DKGRAY);
        tanButton.setTextColor(Color.YELLOW);
        divideButton.setBackgroundColor(Color.DKGRAY);
        divideButton.setTextColor(Color.YELLOW);
        multiplyButton.setBackgroundColor(Color.DKGRAY);
        multiplyButton.setTextColor(Color.YELLOW);
        addButton.setBackgroundColor(Color.DKGRAY);
        addButton.setTextColor(Color.YELLOW);
        subtractButton.setBackgroundColor(Color.DKGRAY);
        subtractButton.setTextColor(Color.YELLOW);
        decimalButton.setBackgroundColor(Color.DKGRAY);
        decimalButton.setTextColor(Color.YELLOW);
        mButton.setBackgroundColor(Color.DKGRAY);
        mButton.setTextColor(Color.YELLOW);
        signButton.setBackgroundColor(Color.DKGRAY);
        signButton.setTextColor(Color.YELLOW);
        zeroButton.setBackgroundColor(Color.DKGRAY);
        zeroButton.setTextColor(Color.YELLOW);
        oneButton.setBackgroundColor(Color.DKGRAY);
        oneButton.setTextColor(Color.YELLOW);
        twoButton.setBackgroundColor(Color.DKGRAY);
        twoButton.setTextColor(Color.YELLOW);
        threeButton.setBackgroundColor(Color.DKGRAY);
        threeButton.setTextColor(Color.YELLOW);
        fourButton.setBackgroundColor(Color.DKGRAY);
        fourButton.setTextColor(Color.YELLOW);
        fiveButton.setBackgroundColor(Color.DKGRAY);
        fiveButton.setTextColor(Color.YELLOW);
        sixButton.setBackgroundColor(Color.DKGRAY);
        sixButton.setTextColor(Color.YELLOW);
        sevenButton.setBackgroundColor(Color.DKGRAY);
        sevenButton.setTextColor(Color.YELLOW);
        eightButton.setBackgroundColor(Color.DKGRAY);
        eightButton.setTextColor(Color.YELLOW);
        nineButton.setBackgroundColor(Color.DKGRAY);
        nineButton.setTextColor(Color.YELLOW);
        piButton.setTextColor(Color.YELLOW);
        piButton.setBackgroundColor(Color.DKGRAY);

        //updating text boxes
        mEqualsOutput.setTextColor(Color.YELLOW);
        mEqualsOutput.setTextColor(Color.YELLOW);
        mEqualsOutput.setBackgroundColor(Color.BLACK);
        degRadOutput.setTextColor(Color.YELLOW);
        lightDarkOutput.setTextColor(Color.YELLOW);
        equationHistoryOutput.setTextColor(Color.YELLOW);
        userInputOutput.setTextColor(Color.YELLOW);
        //scrollview update
        backgroundScroll.setBackgroundColor(Color.BLACK);
    }

    //evaluate method for processing operations, called by equals or operation button
    void evaluate(){
        //breaking if there is no input
        if(currentInput.equals("") != true && currentInput.equals("-") != true){
            //processing input for decimal numbers and pi
            double currentInputDouble;
            if(currentInput.equals("π")){ //if pi, use pi constant.
                currentInputDouble = Math.PI;
            }else{//if not pi, use input.
                currentInputDouble = Double.parseDouble(currentInput);
            }

            //if no previous input has been given, the first given number is loaded in
            if(currentState == cycleState.start){
                //setting the current state to need operator
                currentState = cycleState.needOperator;
                //processing input
                currentValue = currentInputDouble;
                inputHistory = currentInput;
                equationHistoryOutput.setText(inputHistory);
                currentInput = "";
                userInputOutput.setText("");
                //Toast.makeText(MainActivity.this , String.valueOf(currentValue), Toast.LENGTH_SHORT).show();//test code
            }else if (currentState == cycleState.needOperator){
                //processing the math to make room for the next operator

                //course of action depends on next steps.
                switch (currentOperation) {
                    case none:
                        //Toast.makeText(MainActivity.this , "Please input an Operator!", Toast.LENGTH_SHORT).show();
                        break;
                    case add:
                        //handling addition and updating text
                        currentValue += currentInputDouble;
                        if(equationHistoryOutput.equals("") != true){
                            inputHistory = inputHistory + "+" + currentInput;
                        }
                        equationHistoryOutput.setText(inputHistory + "=" + decFormatter.format(currentValue));
                        currentInput = "";
                        userInputOutput.setText("");
                        currentState = cycleState.needValue;
                        break;
                    case subtract:
                        //handling subtraction and updating text
                        currentValue -= currentInputDouble;
                        if(equationHistoryOutput.equals("") != true){
                            inputHistory = inputHistory + "-" + currentInput;
                        }
                        equationHistoryOutput.setText(inputHistory + "=" + decFormatter.format(currentValue));
                        currentInput = "";
                        userInputOutput.setText("");
                        currentState = cycleState.needValue;
                        break;
                    case divide:
                        //handling division and updating text
                        if(currentInputDouble != 0){ //if not dividing by zero
                            //if division is possible, continue with normal operations
                            currentValue = currentValue / currentInputDouble;
                            if(equationHistoryOutput.equals("") != true){
                                inputHistory = inputHistory + "÷" + currentInput;
                            }
                            equationHistoryOutput.setText(inputHistory + "=" + decFormatter.format(currentValue));
                            currentState = cycleState.needValue;
                        }else{
                            //if division is not possible, go back to requesting operator etc.
                            Toast.makeText(MainActivity.this , "Cannot Divide by Zero", Toast.LENGTH_SHORT).show();
                            equationHistoryOutput.setText(inputHistory);
                            currentState = cycleState.needOperator;
                        }
                        //resuming standard reset operations
                        currentInput = "";
                        userInputOutput.setText("");
                        break;
                    case multiply:
                        //handling multiplication and updating text
                        currentValue = currentValue * currentInputDouble;
                        if(equationHistoryOutput.equals("") != true){
                            inputHistory = inputHistory + "X" + currentInput;
                        }
                        equationHistoryOutput.setText(inputHistory + "=" + decFormatter.format(currentValue));
                        currentInput = "";
                        userInputOutput.setText("");
                        currentState = cycleState.needValue;
                        break;
                    case sin:
                        //value in radians
                        double valueInRads = currentInputDouble;
                        String degOrRadsString = "rad";
                        if(degRad == true){ //ensuring value is in radians
                            valueInRads = Math.toRadians(valueInRads);
                            degOrRadsString = "°";//updating text
                        }
                        //calculating sin and updating values
                        currentValue = currentValue * Math.sin(valueInRads);
                        inputHistory = inputHistory + "sin" + currentInput + degOrRadsString;
                        equationHistoryOutput.setText(inputHistory + "=" + decFormatter.format(currentValue));
                        currentInput = "";
                        userInputOutput.setText("");
                        currentState = cycleState.needValue;
                        break;
                    case cos:
                        //value in radians
                        double valueInRadsCos = currentInputDouble;
                        String degOrRadsStringCos = "rad";
                        if(degRad == true){ //ensuring value is in radians
                            valueInRadsCos = Math.toRadians(valueInRadsCos);
                            degOrRadsStringCos = "°";//updating text
                        }
                        //calculating cos and updating values
                        currentValue = currentValue * Math.cos(valueInRadsCos);
                        inputHistory = inputHistory + "cos" + currentInput + degOrRadsStringCos;
                        equationHistoryOutput.setText(inputHistory + "=" + decFormatter.format(currentValue));
                        currentInput = "";
                        userInputOutput.setText("");
                        currentState = cycleState.needValue;
                        break;
                    case tan:
                        //value in radians
                        double valueInRadsTan = currentInputDouble;
                        String degOrRadsStringTan = "rad";
                        if(degRad == true){ //ensuring value is in radians
                            valueInRadsTan = Math.toRadians(valueInRadsTan);
                            degOrRadsStringTan = "°";//updating text
                        }
                        //calculating tan and updating values
                        currentValue = currentValue * Math.tan(valueInRadsTan);
                        inputHistory = inputHistory + "tan" + currentInput + degOrRadsStringTan;
                        equationHistoryOutput.setText(inputHistory + "=" + decFormatter.format(currentValue));
                        currentInput = "";
                        userInputOutput.setText("");
                        currentState = cycleState.needValue;
                        break;
                    default:
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigning button variables to view elements
        //number buttons
        zeroButton = (Button) findViewById(R.id.zeroButton);
        oneButton = (Button) findViewById(R.id.button1);
        twoButton = (Button) findViewById(R.id.button2);
        threeButton = (Button) findViewById(R.id.button3);
        fourButton = (Button) findViewById(R.id.button4);
        fiveButton = (Button) findViewById(R.id.button5);
        sixButton = (Button) findViewById(R.id.button6);
        sevenButton = (Button) findViewById(R.id.button7);
        eightButton = (Button) findViewById(R.id.button8);
        nineButton = (Button) findViewById(R.id.button9);
        //m buttons
        mButton = (Button) findViewById(R.id.mButton);
        setMButton = (Button) findViewById(R.id.mSetButton);
        //clear buttons
        clearButton = (Button) findViewById(R.id.clearButton);
        clearAllButton = (Button) findViewById(R.id.clearAllButton);
        //operation buttons
        signButton = (Button) findViewById(R.id.signButton);
        addButton = (Button) findViewById(R.id.addButton);
        subtractButton = (Button) findViewById(R.id.subtractButton);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        divideButton = (Button) findViewById(R.id.divideButton);
        //decimal and equals button
        decimalButton = (Button) findViewById(R.id.decimalButton);
        equalsButton = (Button) findViewById(R.id.equalsButton);
        //trig buttons
        sinButton = (Button) findViewById(R.id.sinButton);
        cosButton = (Button) findViewById(R.id.cosButton);
        tanButton = (Button) findViewById(R.id.tanButton);
        radDegButton = (Button) findViewById(R.id.radDegButton);
        piButton = (Button) findViewById(R.id.piButton);
        //light/dark button
        lightDarkButton = (Button) findViewById(R.id.lightDarkButton);
        //now for the text output
        mEqualsOutput = (TextView) findViewById(R.id.mEqualsOutput);
        degRadOutput = (TextView) findViewById(R.id.degRadOutput);
        lightDarkOutput = (TextView) findViewById(R.id.colorModeOutput);
        equationHistoryOutput = (TextView) findViewById(R.id.equationHistoryOutput);
        userInputOutput = (TextView) findViewById(R.id.userInputOutput);

        //scrollview for background
        backgroundScroll = (ScrollView) findViewById(R.id.bigScrollView);

        //switching color mode to light mode
        lightMode();

        //listeners for the buttons zero-nine
        zeroButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inputChecker()){//checking if input can be received
                    //Adding a zero to the current input
                    currentInput = currentInput + "0";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        oneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inputChecker()){//checking if input can be received
                    //Adding a one to the current input
                    currentInput = currentInput + "1";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        twoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inputChecker()){//checking if input can be received
                    //Adding a 2 to the current input
                    currentInput = currentInput + "2";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        threeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inputChecker()){//checking if input can be received
                    //Adding a 3 to the current input
                    currentInput = currentInput + "3";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        fourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inputChecker()){//checking if input can be received
                    //Adding a 4 to the current input
                    currentInput = currentInput + "4";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        fiveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inputChecker()){//checking if more input can be accepted
                    //Adding a 5 to the current input
                    currentInput = currentInput + "5";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        sixButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inputChecker()){//checking if input can be received
                    //Adding a 6 to the current input
                    currentInput = currentInput + "6";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        sevenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//checking if input can be received
                if(inputChecker()){
                    //Adding a 7 to the current input
                    currentInput = currentInput + "7";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        eightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(inputChecker()){//checking if input can be received
                    //Adding an 8 to the current input
                    currentInput = currentInput + "8";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });
        nineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //checking if input can be received
                if(inputChecker()){
                    //Adding a 9 to the current input
                    currentInput = currentInput + "9";
                    userInputOutput.setText(currentInput);
                    //updating that a value has been added
                    if(currentState == cycleState.needValue){
                        currentState = cycleState.needOperator;
                    }
                }
            }
        });

        //clear and clear all buttons
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //reset to default values
                posNeg = true;
                inputHistory = "";
                currentInput = "";
                currentValue = 0;
                m = 1;
                currentOperation = MainActivity.operation.none; //holds current operation
                currentState = MainActivity.cycleState.start;//reset to start state
                //resetting the text fields to default
                mEqualsOutput.setText("M = 1");
                equationHistoryOutput.setText("");
                userInputOutput.setText("");
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //reset user input to default values
                posNeg = true;
                currentInput = "";
                //currentValue = 0;
                userInputOutput.setText("");
            }
        });

        //set M and M button
        setMButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //checking if valid input has been given
                if(currentInput.equals("") != true && currentInput.equals("-") != true){
                    //set m to input and update text
                    m = Double.parseDouble(currentInput);
                    mEqualsOutput.setText("M = " + String.valueOf(m));//setting m output value
                    //resetting values
                    posNeg = true;
                    currentInput = "";
                    userInputOutput.setText("");
                }else{
                    Toast.makeText(MainActivity.this , "Invalid M Value", Toast.LENGTH_SHORT).show();//error
                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Adding a one to the current input
                currentInput = String.valueOf(m);
                userInputOutput.setText(currentInput);
                //IMPORTANT _------------------------------ADD pos/ NEG SIGN PROCESSING-----------------IMPORTANT
                //updating that a value has been added
                if(currentState == cycleState.needValue){
                    currentState = cycleState.needOperator;
                }
            }
        });

        //positive/negative buttons.
        signButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(userInputOutput.getText().equals("")){//if input is empty
                    currentInput = "-";//set input to negative sign
                }else if(userInputOutput.getText().charAt(0) == '-'){//if input is negative
                    //removing negative sign from input string
                    currentInput = currentInput.substring(1);
                }else{//if there is input that is not negative
                    //making input negative
                    currentInput = "-" + currentInput;
                }//updating the user input output field
                userInputOutput.setText(currentInput);
            }
        });

        //Decimal button
        decimalButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(userInputOutput.getText().equals("")){//if input is empty
                    currentInput = "0.";//set input to zero decimal
                }else if(userInputOutput.getText().charAt(0) == '-'){//if input is negative sign only
                    //set input to negative zero decimal
                    currentInput = "-0.";
                }else if(currentInput.contains(".") == false){//if there is no decimal sign in the number
                    //add decimal
                    currentInput = currentInput + ".";
                }//updating the user input output field
                userInputOutput.setText(currentInput);
            }
        });

        //basic operations button listeners
        //Evaluate previous input, set current operation, and update history output
        equalsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //evaluate input and operation
                if(currentInput.equals("") || currentInput.equals("-")){
                    Toast.makeText(MainActivity.this , "Invalid input", Toast.LENGTH_SHORT).show();
                }else {
                    evaluate();
                    currentOperation = operation.none;
                }

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if input is valid but does not yet need processing
                if((currentInput.equals("") || currentInput.equals("-")) && inputHistory.equals("") != true){
                    currentOperation = operation.add;
                    equationHistoryOutput.setText(inputHistory + "+");
                }else if((currentInput.equals("") || currentInput.equals("-")) && inputHistory.equals("")){
                    //invalid input
                    Toast.makeText(MainActivity.this , "Invalid input", Toast.LENGTH_SHORT).show();
                }else{//if input is valid and needs evaluating
                    evaluate();
                    currentOperation = operation.add;
                    equationHistoryOutput.setText(inputHistory + "+");
                }
            }
        });
        subtractButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if input is valid but does not yet need processing
                if((currentInput.equals("") || currentInput.equals("-")) && inputHistory.equals("") != true){
                    currentOperation = operation.subtract;
                    equationHistoryOutput.setText(inputHistory + "-");
                }else if((currentInput.equals("") || currentInput.equals("-")) && inputHistory.equals("")){
                    //invalid input
                    Toast.makeText(MainActivity.this , "Invalid input", Toast.LENGTH_SHORT).show();
                }else{//if input is valid and needs evaluating
                    evaluate();
                    currentOperation = operation.subtract;
                    equationHistoryOutput.setText(inputHistory + "-");
                }
            }
        });
        divideButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if input is valid but does not yet need processing
                if((currentInput.equals("") || currentInput.equals("-")) && inputHistory.equals("") != true){
                    currentOperation = operation.divide;
                    equationHistoryOutput.setText(inputHistory + "÷");
                }else if((currentInput.equals("") || currentInput.equals("-")) && inputHistory.equals("")){
                    //invalid input makes  a toast message
                    Toast.makeText(MainActivity.this , "Invalid input", Toast.LENGTH_SHORT).show();
                }else{//if input is valid and needs evaluating
                    evaluate();
                    currentOperation = operation.divide;
                    equationHistoryOutput.setText(inputHistory + "÷");
                }
            }
        });
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if input is valid but does not yet need processing
                if((currentInput.equals("") || currentInput.equals("-")) && inputHistory.equals("") != true){
                    currentOperation = operation.multiply;
                    equationHistoryOutput.setText(inputHistory + "X");
                }else if((currentInput.equals("") || currentInput.equals("-")) && inputHistory.equals("")){
                    //invalid input
                    Toast.makeText(MainActivity.this , "Invalid input", Toast.LENGTH_SHORT).show();
                }else{//if input is valid and needs evaluating
                    evaluate();
                    currentOperation = operation.multiply;
                    equationHistoryOutput.setText(inputHistory + "X");
                }
            }
        });

        //deg/rad button listener
        radDegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //switch from radians to degrees or vice versa
                //true = deg, false = rad
                if(degRad == true){
                    degRad = false;
                    degRadOutput.setText("Rad");
                }else{
                    degRad = true;
                    degRadOutput.setText("Deg");
                }
            }
        });

        //Trig functions buttons
        sinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //handling if sin is the first operation
                if(inputHistory.equals("") && userInputOutput.getText().equals("")){//if there is no previous input, do sin(x)
                    //currentInput = "1";
                    currentValue = 1;
                    currentState = cycleState.needValue;
                }//if there is no previous input, but there is a negative sign, set current value to -1
                else if (inputHistory.equals("") && userInputOutput.getText().equals("-")){
                    //currentInput = "-1";
                    currentValue = -1;
                    currentState = cycleState.needValue;
                    inputHistory += "-";
                    equationHistoryOutput.setText(inputHistory);
                    userInputOutput.setText("");
                    currentInput = "";
                }else{
                    //handling negative trig functions after first input
                    if(currentInput.equals("-")){
                        currentValue *= -1;
                        inputHistory += "(-1)";
                        currentInput = "";
                        userInputOutput.setText("");
                    }
                    evaluate();
                }
                currentOperation = operation.sin;
                equationHistoryOutput.setText(inputHistory + "sin");
            }
        });
        cosButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //handling if cos is the first operation
                if(inputHistory.equals("") && userInputOutput.getText().equals("")){//if there is no previous input, do cos(x)
                    currentValue = 1;
                    currentState = cycleState.needValue;
                }//if there is no previous input, but there is a negative sign, set current value to -1
                else if (inputHistory.equals("") && userInputOutput.getText().equals("-")){
                    currentValue = -1;
                    currentState = cycleState.needValue;
                    inputHistory = "-";
                    equationHistoryOutput.setText("-");
                    userInputOutput.setText("");
                    currentInput = "";
                }else{
                    //handling negative trig functions after first input
                    if(currentInput.equals("-")){
                        currentValue *= -1;
                        inputHistory += "(-1)";
                        currentInput = "";
                        userInputOutput.setText("");
                    }
                    evaluate();
                }
                currentOperation = operation.cos;
                equationHistoryOutput.setText(inputHistory + "cos");
            }
        });
        tanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //handling if tan is the first operation
                if(inputHistory.equals("") && userInputOutput.getText().equals("")){//if there is no previous input, do cos(x)
                    currentValue = 1;
                    currentState = cycleState.needValue;
                }//if there is no previous input, but there is a negative sign, set current value to -1
                else if (inputHistory.equals("") && userInputOutput.getText().equals("-")){
                    currentValue = -1;
                    currentState = cycleState.needValue;
                    inputHistory = "-";
                    equationHistoryOutput.setText("-");
                    userInputOutput.setText("");
                    currentInput = "";
                }else{
                    //handling negative trig functions after first input
                    if(currentInput.equals("-")){
                        currentValue *= -1;
                        inputHistory += "(-1)";
                        currentInput = "";
                        userInputOutput.setText("");
                    }
                    evaluate();
                }
                currentOperation = operation.tan;
                equationHistoryOutput.setText(inputHistory + "tan");
            }
        });

        //light/dark button
        lightDarkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(lightDark == true){
                    lightDark = false;
                    darkMode(); //switching to dark mode
                }else{
                    lightDark = true;
                    lightMode();//switch to light
                }

            }
        });

        //pi button
        piButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Adding a one to the current input
                currentInput = "π";
                userInputOutput.setText(currentInput);
                //IMPORTANT _------------------------------ADD pos/ NEG SIGN PROCESSING-----------------IMPORTANT
                //updating that a value has been added
                if(currentState == cycleState.needValue){
                    currentState = cycleState.needOperator;
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //saving text box strings
        outState.putString("saveMEO", (String) mEqualsOutput.getText());
        outState.putString("saveDRO", (String) degRadOutput.getText());
        outState.putString("saveLDO", (String) lightDarkOutput.getText());
        outState.putString("saveEHO", (String) equationHistoryOutput.getText());
        outState.putString("saveUIO", (String) userInputOutput.getText());

        //saving other strings
        outState.putString("saveIH", inputHistory);
        outState.putString("saveCI", currentInput);

        //saving program boolean variables
        outState.putBoolean("saveLD", lightDark);
        outState.putBoolean("savePN", posNeg);
        outState.putBoolean("saveDR", degRad);

        //saving double values
        outState.putDouble("saveM", m);
        outState.putDouble("saveCV", currentValue);

        //saving enum states
        outState.putSerializable("saveCS", currentState);
        outState.putSerializable("saveCO", currentOperation);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //restoring text box contents
        mEqualsOutput.setText(savedInstanceState.getString("saveMEO"));
        degRadOutput.setText(savedInstanceState.getString("saveDRO"));
        lightDarkOutput.setText(savedInstanceState.getString("saveLDO"));
        equationHistoryOutput.setText(savedInstanceState.getString("saveEHO"));
        userInputOutput.setText(savedInstanceState.getString("saveUIO"));

        //restoring other strings
        inputHistory = savedInstanceState.getString("saveIH");
        currentInput = savedInstanceState.getString("saveCI");

        //restoring booleans
        lightDark = savedInstanceState.getBoolean("saveLD");
        posNeg = savedInstanceState.getBoolean("savePN");
        degRad = savedInstanceState.getBoolean("saveDR");

        //restoring doubles
        m = savedInstanceState.getDouble("saveM");
        currentValue = savedInstanceState.getDouble("saveCV");

        //restoring enums
        currentState = (cycleState) savedInstanceState.getSerializable("saveCS");
        currentOperation = (operation) savedInstanceState.getSerializable("saveCO");

        //ensuring that the decimal formatter is carried over
        decFormatter = new DecimalFormat("#.######");

        //updating color mode if in dark mode
        if(lightDark == false){
            darkMode();
        }
    }
}