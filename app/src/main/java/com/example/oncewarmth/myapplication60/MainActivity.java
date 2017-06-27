package com.example.oncewarmth.myapplication60;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button delBtn;
    private Button dotBtn;

    private Button addBtn;
    private Button reduceBtn;
    private Button multiplyBtn;
    private Button divideBtn;

    private Button clearBtn;
    private Button equalBtn;

    private boolean isEnter;
    private boolean isClickSymbol = false;
    private boolean numInput = false;

    private TextView display;

    Double stackDouble = 0.0;
    String stackSymbol = "";
    private boolean isStackEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
        delBtn = (Button)findViewById(R.id.deleteBtn);
        dotBtn = (Button)findViewById(R.id.dotBtn);
        display = (TextView)findViewById(R.id.display);
        addBtn = (Button)findViewById(R.id.addButton);
        reduceBtn = (Button)findViewById(R.id.reduceBtn);
        multiplyBtn = (Button)findViewById(R.id.multiplyBtn);
        divideBtn = (Button)findViewById(R.id.divideBtn);
        clearBtn = (Button)findViewById(R.id.clearBtn);
        equalBtn = (Button)findViewById(R.id.equelBtn);


        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        delBtn.setOnClickListener(this);
        dotBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        reduceBtn.setOnClickListener(this);
        multiplyBtn.setOnClickListener(this);
        divideBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        equalBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button0:
                clickNumberButton(0);
                break;
            case R.id.button1:
                clickNumberButton(1);
                break;
            case R.id.button2:
                clickNumberButton(2);
                break;
            case R.id.button3:
                clickNumberButton(3);
                break;
            case R.id.button4:
                clickNumberButton(4);
                break;
            case R.id.button5:
                clickNumberButton(5);
                break;
            case R.id.button6:
                clickNumberButton(6);
                break;
            case R.id.button7:
                clickNumberButton(7);
                break;
            case R.id.button8:
                clickNumberButton(8);
                break;
            case R.id.button9:
                clickNumberButton(9);
                break;
            case R.id.dotBtn:
                clickDotButton();
                break;
            case R.id.deleteBtn:
                clickdeleteButton();
                break;
            case R.id.addButton:
                clickAddButton();
                break;
            case R.id.divideBtn:
                clickDivideButton();
                break;
            case R.id.reduceBtn:
                clickReduceButton();
                break;
            case R.id.multiplyBtn:
                clickMultiplyButton();
                break;
            case R.id.clearBtn:
                clickClearButton();
                break;
            case R.id.equelBtn:
                clickEqualBtn();
                break;
        }
    }

    private void clickEqualBtn() {
        if (isEnter)
            return ;
        if (isClickSymbol)
            return ;
        if (isStackEmpty)
            return ;
        if (!numInput)
            return;;
        String displayText = display.getText().toString();
        Double value;
        try {
            value = Double.valueOf(displayText);
        } catch (Exception e) {
            Toast.makeText(this, "输入字符不合法", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(!stackAction(value)) {
            return ;
        }
        display.setText(stackDouble.toString());
        isEnter = true;
    }

    private void clickClearButton() {
        display.setText("0");
        isClickSymbol = false;
        isStackEmpty = true;
        isEnter = false;
        numInput = false;
        stackSymbol  = "";
        stackDouble = 0.0;
    }

    private void clickNumberButton(int value) {
        String displayText = display.getText().toString();
        if (isClickSymbol) {
            displayText = "";
            isClickSymbol = false;
        }

        if (isEnter) {
            displayText = "";
            isEnter = false;
        }

        if (numInput) {
            displayText = displayText + String.valueOf(value);
        } else {
            displayText = String.valueOf(value);
            numInput = true;
        }

        display.setText(displayText);
    }

    private void clickdeleteButton() {
        String displayText = display.getText().toString();
        if (isClickSymbol) {
            display.setText("");
            isClickSymbol = false;
            return ;
        }

        if (isEnter) {
            display.setText("");
            isEnter = false;
            return ;
        }


        if (!numInput) {
            return ;
        }

        if (displayText.isEmpty())
            return ;

        displayText = displayText.substring(0, displayText.length()-1);

        display.setText(displayText);
    }
    private void clickDotButton() {
        String displayText = display.getText().toString();
        if (isClickSymbol)
            return ;
        if (isEnter)
            return;;
        if (displayText.isEmpty())
            return ;

        displayText = displayText + ".";
        numInput = true;
        display.setText(displayText);
    }
    private void clickAddButton() {
        String displayText = display.getText().toString();
        Double value;
        try {
            value = Double.valueOf(displayText);
        } catch (Exception e) {
            Toast.makeText(this, "输入字符不合法", Toast.LENGTH_SHORT).show();
            return ;
        }

        if (isEnter) {
            stackSymbol = "+";
            isClickSymbol = true;
            isEnter = false;
            return ;
        }

        if (isClickSymbol) {
            stackSymbol = "+";
            return ;
        }

        if (isStackEmpty) {
            stackDouble = value;
            isStackEmpty = false;
            stackSymbol = "+";
        } else {
            if(!stackAction(value)) {
                return ;
            }
            stackSymbol = "+";
        }
        isClickSymbol = true;
        display.setText(stackDouble.toString());
    }
    private void clickReduceButton() {
        String displayText = display.getText().toString();
        Double value;
        try {
            value = Double.valueOf(displayText);
        } catch (Exception e) {
            Toast.makeText(this, "输入字符不合法", Toast.LENGTH_SHORT).show();
            return ;
        }

        if (isEnter) {
            stackSymbol = "-";
            isClickSymbol = true;
            isEnter = false;
            return ;
        }

        if (isClickSymbol) {
            stackSymbol = "-";
            return ;
        }

        if (isStackEmpty) {
            stackDouble = value;
            isStackEmpty = false;
            stackSymbol = "-";
        } else {
            if(!stackAction(value)) {
                return ;
            }
            stackSymbol = "-";
        }
        isClickSymbol = true;
        isEnter = false;
        display.setText(stackDouble.toString());
    }
    private void clickDivideButton() {
        String displayText = display.getText().toString();
        Double value;
        try {
            value = Double.valueOf(displayText);
        } catch (Exception e) {
            Toast.makeText(this, "输入字符不合法", Toast.LENGTH_SHORT).show();
            return ;
        }

        if (isEnter) {
            stackSymbol = "%";
            isClickSymbol = true;
            isEnter = false;
            return ;
        }

        if (isClickSymbol) {
            stackSymbol = "%";
            return ;
        }

        if (isStackEmpty) {
            stackDouble = value;
            isStackEmpty = false;
            stackSymbol = "%";
        } else {
            if(!stackAction(value)) {
                return ;
            }
            stackSymbol = "%";
        }
        isClickSymbol = true;
        isEnter = false;
        display.setText(stackDouble.toString());
    }
    private void clickMultiplyButton() {
        String displayText = display.getText().toString();
        Double value;
        try {
            value = Double.valueOf(displayText);
        } catch (Exception e) {
            Toast.makeText(this, "输入字符不合法", Toast.LENGTH_SHORT).show();
            return ;
        }

        if (isEnter) {
            stackSymbol = "*";
            isClickSymbol = true;
            isEnter = false;
            return ;
        }

        if (isClickSymbol) {
            stackSymbol = "*";
            return ;
        }

        if (isStackEmpty) {
            stackDouble = value;
            isStackEmpty = false;
            stackSymbol = "*";
        } else {
            if(!stackAction(value)) {
                return ;
            }
            stackSymbol = "*";
        }
        isClickSymbol = true;
        isEnter = false;
        display.setText(stackDouble.toString());
    }

    private boolean stackAction(Double value) {
        if (stackSymbol.equals("*")) {
            stackDouble = stackDouble * value;
        } else if (stackSymbol.equals("%")) {
            if (value.equals(0.0)) {
                Toast.makeText(this, "输入字符不合法", Toast.LENGTH_SHORT).show();
                return false;
            }
            stackDouble = stackDouble / value;
        } else if (stackSymbol.equals("+")) {
            stackDouble = stackDouble + value;
        } else if (stackSymbol.equals("-")) {
            stackDouble = stackDouble - value;
        }
        return true;
    }
}
