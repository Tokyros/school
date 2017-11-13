package com.company;

/**
 * Created by SBK on 11/9/2017.
 */
public class SandBox {
    public static void main(String[] args) {
        getMostFrequentDigit(2459784);
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
