package com.android3project.dev.mycal.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Dev on 7/16/2015.
 */
public class DataDomain implements Parcelable {
    private String firstNumber;
    private String secondNumber;
    private String operator;
    private double result;

    //Constructor
    public DataDomain(String firstNumber, String secondNumber, String operator) {
        setOperand(firstNumber, secondNumber);
        setOperator(operator);
    }

    //Setter
    public DataDomain setOperand(String firstOperand, String secondOperand){
        try {
            this.firstNumber = String.valueOf(getNumFormat(firstOperand));
            this.secondNumber = String.valueOf(getNumFormat(secondOperand));
        } catch (ParseException e) {
            this.firstNumber = firstOperand;
            this.secondNumber = secondOperand;
        }
        return this;
    }
    public DataDomain setOperator(String operator){
        this.operator = operator;
        return this;
    }

    //Getters
    public String getFirstNumber() {
        return firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public String getResult() {
        return roundUpTwoDecimalPlaces(compute());
    }

    public Number getNumFormat(String number) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setGroupingUsed(true);
        return numberFormat.parse(number);
    }

    public double compute(){
        double firstNum = 0;
        double secondNum = 0;
        String operator = null;
        try {
            firstNum = getNumFormat(getFirstNumber()).doubleValue();
            secondNum = getNumFormat(getSecondNumber()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        operator = String.valueOf(getOperator().charAt(0));
        double result = 0;

        if (!isComputable()) {
            return -1D;
        } else {
            switch (operator) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "x":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    result = firstNum / secondNum;
                    break;
            }
            return result;
        }
    }

    public boolean isComputable() {
        if (!TextUtils.isEmpty(getFirstNumber()) && !TextUtils.isEmpty(getSecondNumber()) && !TextUtils.isEmpty(getOperator())) {
            try {
                getNumFormat(getFirstNumber()).toString();
                getNumFormat(getSecondNumber()).toString();
            } catch (ParseException parseexception) {
                return false;
            }
            if (getOperator().length() == 1) {
                char c1 = getOperator().charAt(0);
                if (c1 == '+' || c1 == '-' || c1 == 'x' || c1 == '/') {
                    return true;
                }
            }
        }
        return false;
    }

    public String roundUpTwoDecimalPlaces(double result){
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstNumber);
        dest.writeString(this.secondNumber);
        dest.writeString(this.operator);
        dest.writeDouble(this.result);
    }

    protected DataDomain(Parcel in) {
        this.firstNumber = in.readString();
        this.secondNumber = in.readString();
        this.operator = in.readString();
        this.result = in.readDouble();
    }

    public static final Creator<DataDomain> CREATOR = new Creator<DataDomain>() {
        public DataDomain createFromParcel(Parcel source) {
            return new DataDomain(source);
        }

        public DataDomain[] newArray(int size) {
            return new DataDomain[size];
        }
    };
}
