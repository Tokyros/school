package com.company;

import javax.swing.JOptionPane;

public class HW1_ShaharRosen {
    public static void main(String[] args) {
        boolean shouldPlay;

        do {
            String playerNamePrompt = "Please enter full name for player #%s:";
            String player1 = "Player #1, " + JOptionPane.showInputDialog(String.format(playerNamePrompt, 1));
            String player2 = "Player #2, " + JOptionPane.showInputDialog(String.format(playerNamePrompt, 2));
            String practicePrompt = "For Practice:\nHow many secret numbers do you want to show:\nPress 1, 2, 3 or Cancel for none";

            int hintCount = Integer.parseInt(JOptionPane.showInputDialog(practicePrompt));

            int num1, num2, num3;
            num1 = (int) (Math.random() * 4) + 1;

            do {
                num2 = (int) (Math.random() * 4) + 1;
            }
            while (num2 == num1);

            do {
                num3 = (int) (Math.random() * 4) + 1;
            }
            while (num3 == num2 || num3 == num1);

            String hint = "";
            switch (hintCount) {
                case 3:
                    hint += num3;
                case 2:
                    hint += " " + num2;
                case 1:
                    hint += " " + num1;
                default:
                    hint = hint.trim();
            }

            System.out.println(hint);

            String currentPlayer = player2;

            //Flag to keep track if the player won the game, used to stop the game and announce the winner.
            boolean gameWon = false;
            do {
                //Toggle the current player.
                if (currentPlayer.equals(player1)) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
                }

                //Variables to keep track of the user's current guesses
                int guess1 = 0;
                int guess2 = 0;

                //Variables to keep track of how many
                int numOfGuesses = 0;
                int correctGuesses = 0;

                while (numOfGuesses <= 2) {
                    boolean duplicateGuessFlag = false;
                    int currentGuess = Integer.parseInt(JOptionPane.showInputDialog("Guess a num - " + currentPlayer));
                    if (numOfGuesses == 0) {
                        guess1 = currentGuess;
                    } else if (numOfGuesses == 1) {
                        guess2 = currentGuess;
                        if (currentGuess == guess1) {
                            duplicateGuessFlag = true;
                        }
                    } else {
                        if (currentGuess == guess2 || currentGuess == guess1) {
                            duplicateGuessFlag = true;
                        }
                    }

                    if (duplicateGuessFlag) {
                        JOptionPane.showMessageDialog(null, "Don't choose the same number twice", "Number already chosen", JOptionPane.ERROR_MESSAGE);
                    } else {
                        numOfGuesses++;
                        if (currentGuess == num1 || currentGuess == num2 || currentGuess == num3) {
                            correctGuesses++;
                        }
                    }
                }
                if (correctGuesses == 3) {
                    gameWon = true;
                }
            } while (!gameWon);
            shouldPlay = JOptionPane.showConfirmDialog(null, "Congratulations!\n" + currentPlayer + " Won!\nDo you want to start a new game?") == 0;
        } while ((shouldPlay));
    }
}
