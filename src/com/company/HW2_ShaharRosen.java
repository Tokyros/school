package com.company;

import javax.swing.*;
import java.util.Arrays;

/**
 * Created by SBK on 11/20/2017.
 */
public class HW2_ShaharRosen {

    public static void main(String[] args) {
//        int[] inputArr = new int[4];
//        for (int i = 0; i < inputArr.length; i++) {
//            inputArr[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter Number " + i));
//        }
//        int[][] matA = getRandomMatrix(inputArr[0], inputArr[1]);
//        int[][] matB = getRandomMatrix(inputArr[2], inputArr[3]);
//        int[][] multi = multiplyMatrices(matA, matB);
//        final String multiTransposed = convertMatrixToString(transposeMatrix(multi));
//
//        JOptionPane.showMessageDialog(null, convertMatrixToString(multi));
//        JOptionPane.showMessageDialog(null, multiTransposed);
//        System.out.println(convertDecimalToBinary(16));
//        System.out.println(convertDecimalToBinary(24));
//        System.out.println(convertDecimalToBinary(26));
//        System.out.println(convertBinaryToDecimal(1001));
//        System.out.println(convertBinaryToDecimal(1111));
//        System.out.println(convertBinaryToDecimal(1101));
//        System.out.println(convertDecimalToHex(359656));
//        final char[] chars = {'7', 'F'};
//        System.out.println(convertHexToDecimal(chars));
    }

    private static int convertHexToDecimal(char[] hex){
        int power = 0;
        int decimal = 0;
        for (int i = hex.length - 1; i >= 0; i--) {
            char c = hex[i];
            int numericValue;
            if (c >= 48 && c <= 57){
                numericValue = (int) c - 48;
            } else {
                numericValue = ((int) c) - 55;
            }
            decimal += numericValue * Math.pow(16, power);
            power++;
        }
        return decimal;
    }

    private static String convertDecimalToHex(int decimal){
        int A = 55;
        String binary = "";
        while (decimal > 0){
            final int i = decimal % 16;
            if (i < 10) binary = i + binary; else binary = (char) (i + A) + binary;
            decimal /= 16;
        }
        return binary;
    }

    private static int convertBinaryToDecimal(int binary){
        int decimal = 0;
        int power = 0;
        while (binary > 0){
            decimal += Math.pow(2, power) * binary % 10;
            binary /= 10;
            power++;
        }
        return decimal;
    }

    private static int convertDecimalToBinary(int decimal){
        int binary = 0;
        int multiplier = 1;
        while (decimal > 0){
            binary += (decimal % 2) * multiplier;
            decimal /= 2;
            multiplier *= 10;
        }
        return binary;
    }

    private static int[][] transposeMatrix(int[][] matrix){
        int[][] transposedMatrix = new int[matrix[0].length][matrix.length];
        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++) {
                transposedMatrix[rowIndex][columnIndex] = matrix[columnIndex][rowIndex];
            }
        }
        return transposedMatrix;
    }

    private static String convertMatrixToString(int[][] matrix){
        String message = "";
        for (int[] rows : matrix) {
            for (int number : rows) {
                message += number + " ";
            }
            message += "\n";
        }
        return message;
    }

    private static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB){
        if (matrixA[0].length != matrixB.length) return null;
        int[][] resultMatrix = new int[matrixA.length][matrixB[0].length];
        for (int rowIndex = 0; rowIndex < matrixA.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < matrixB[0].length; columnIndex++) {
                for (int k = 0; k < matrixB.length; k++) {
                    resultMatrix[rowIndex][columnIndex] += matrixA[rowIndex][k] * matrixB[k][columnIndex];
                }
            }
        }
        return resultMatrix;
    }

    private static int[][] getRandomMatrix(int rows, int columns){
        int[][] randomMatrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            randomMatrix[i] = getRandomArray(columns);
        }
        return randomMatrix;
    }

    private static int[] getRandomArray(int size){
        int[] randomArray = new int[size];
        for (int i = 0; i < size; i++) {
            randomArray[i] = ((int) (Math.random() * 128)) + 1;
        }
        return randomArray;
    }

}
