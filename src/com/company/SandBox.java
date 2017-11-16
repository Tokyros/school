package com.company;

import javax.swing.JOptionPane;

public class SandBox {
    public static void main(String[] args) {
        targil3_Q1();
    }

    public static void counter(){

    }

    private static void targil3_Q1(){
        int shouldPlayAgain;
        do {
            int maxNum = 0;
            boolean moreNumbers = true;
            do {
                String input = JOptionPane.showInputDialog(null, "Gimme a num");
                if (input == null) {
                    moreNumbers = false;
                } else {
                    int currentNum = Integer.parseInt(input);
                    if (currentNum > maxNum) maxNum = currentNum;
                }
            } while (moreNumbers);
            shouldPlayAgain = JOptionPane.showConfirmDialog(null, "Play again?");
        } while (shouldPlayAgain == JOptionPane.YES_OPTION);
    }

    public static int getMostFrequentDigit(int num){
        int[] countArray = new int[10];
            while (num > 0){
                countArray[num % 10]++;
                num /= 10;
            }
        for (int i = 0; i < countArray.length; i++) {
            System.out.println(i + ": " + countArray[i]);
        }
        return 0;
    }
}
