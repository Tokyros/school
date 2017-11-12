package com.company;

/**
 * Created by SBK on 11/9/2017.
 */
public class SandBox {
    public static void main(String[] args) {
        int[] finalArr = new int[0];
        for (int i = 0; i < 100; i++) {
            int[] tempArr = new int[finalArr.length+1];
            for (int i1 = 0; i1 < finalArr.length; i1++) {
                tempArr[i1] = finalArr[i1];
            }
            for (int i1 : tempArr) {
                System.out.println(i1);
            }
            finalArr = new int[finalArr.length+1];
            finalArr[finalArr.length-1] = i;
        }
        for (int i : finalArr) {
        }
    }
}
