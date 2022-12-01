package com.h2;

import java.time.LocalDate;
import java.time.YearMonth;

public class SavingsCalculator {
    private float[] credits;
    private float[] debits;

    //constructor
    public SavingsCalculator(float[] credits, float[] debits) {
        this.credits = credits;
        this.debits = debits;
    }

    //method to total up credits: Private can only be called within the class
    private float sumOfCredits() {
        float sum = 0.0f;
        for(float credit: credits) {
            sum += credit;
        }
        return sum;
    }

    //method to total up debits: Private can only be called within the class
    private float sumOfDebits() {
        float sum = 0.0f;
        for(float debit: debits) {
            sum += debit;
        }
        return sum;
    }

    //method to retrieve the date information fom java.time: Private can only be called within the class
    private static int remainingDaysInMonth(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        int totalDaysInMonth = yearMonth.lengthOfMonth();
        int remainingDays = totalDaysInMonth - date.getDayOfMonth();
        return remainingDays;
    }

    //method to calculate and return the remaining days in the month: Public so we can get this info outside of the class
    public float calculate() {  
        return sumOfCredits() - sumOfDebits();
    }

    public static void main(String[] args) {
        final String[] creditsAsString = args[0].split(",");
        final String[] debitsAsString = args[1].split(",");

       final float[] credits = new float[creditsAsString.length];
       final float[] debits = new float[debitsAsString.length];

       for (int i = 0; i < creditsAsString.length; i++){
           credits[i] = Float.parseFloat(creditsAsString[i]);
       }

       for (int i = 0; i < debitsAsString.length; i++){
           debits[i] = Float.parseFloat(debitsAsString[i]);
       }

       final SavingsCalculator calculator = new SavingsCalculator(credits, debits);
       float netSavings = calculator.calculate();
       System.out.println("Net Savings = " + netSavings + ", remaining days in month = " + remainingDaysInMonth(LocalDate.now()));
    }
}

