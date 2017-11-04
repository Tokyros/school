package com.company;

import javax.swing.*;
import java.util.Random;

/**
 * Created by SBK on 11/2/2017.
 */
public class HW1_ShaharRosen {
    public static void main(String[] args) {
        boolean shouldPlay = true;
        while (shouldPlay){
            final String playerNamePrompt = "Please enter full name for player #%s:";
            String player1 = "Player #1, " +  JOptionPane.showInputDialog(String.format(playerNamePrompt, 1));
            String player2 = "Player #2, " + JOptionPane.showInputDialog(String.format(playerNamePrompt, 2));
            String practicePrompt = "For Practice:\nHow many secret numbers do you want to show:\nPress 1, 2, 3 or Cancel for none";
            String hintCount = JOptionPane.showInputDialog(practicePrompt);

            Random random = new Random();

            String num1 = random.nextInt(4) + 1 + "";

            String num2 = random.nextInt(4) + 1 + "";
            while (num2.equals(num1)){
                num2 = random.nextInt(4) + 1 + "";
            }

            String num3 = random.nextInt(4) + 1 + "";
            while (num3.equals(num2) || num3.equals(num1)){
                num3 = random.nextInt(4) + 1 + "";
            }

            String hint = "";
            switch (hintCount){
                case "1":
                    hint += num1;
                    break;
                case "2":
                    hint += ", " + num2;
                    break;
                case "3":
                    hint += ", " + num3;
                    break;
            }
            System.out.println(hint);

            boolean gameWon = false;
            String winner = "";
            String currentPlayer = player1;

            while (!gameWon){
                String guess1 = null;
                String guess2 = null;

                int numOfGuesses = 0;
                int correctGuesses = 0;

                while (numOfGuesses < 3){
                    String currentGuess = JOptionPane.showInputDialog("Guess a num - " + currentPlayer);
                    if (guess1 == null){
                        guess1 = currentGuess;
                        numOfGuesses++;
                    } else if (guess2 == null){
                        if (currentGuess.equals(guess1)){
                            JOptionPane.showMessageDialog(null, "Don't choose the same number twice", "Number already chosen", JOptionPane.ERROR_MESSAGE);
                            continue;
                        } else {
                            guess2 = currentGuess;
                            numOfGuesses++;
                        }
                    } else {
                        if (currentGuess.equals(guess2)){
                            //Display Error
                            continue;
                        } else {
                            numOfGuesses++;
                        }
                    }
                    if (currentGuess.equals(num1) || currentGuess.equals(num2) || currentGuess.equals(num3)){
                        correctGuesses++;
                    }
                }
                if (correctGuesses == 3){
                    winner = currentPlayer;
                    gameWon = true;
                } else {
                    if (currentPlayer.equals(player1)){
                        currentPlayer = player2;
                    } else {
                        currentPlayer = player1;
                    }
                }
            }
            shouldPlay = JOptionPane.showConfirmDialog(null, "Congratulations!\n" + winner + " Won!\nDo you want to star a new game?") == 0;
        }
    }
}
