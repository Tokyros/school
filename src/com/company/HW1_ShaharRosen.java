//Shahar Rosen - 2045417917
package com.company;

import javax.swing.JOptionPane;

public class HW1_ShaharRosen {

    public static void main(String[] args) {
        final int RANDOM_LIMIT = 5;
        final int CONFIRM = 0;
        final int MAXIMUM_GUESSES_ALLOWED = 3;
        final int RANDOM_NUMBERS_COUNT = 3;

        final String PLAYER_NAME_PROMPT = "Please enter full name for player #%s:";
        final String PRACTICE_PROMPT = "For Practice:\nHow many secret numbers do you want to show:\nPress 1, 2, 3 or Cancel for none";
        final String SHOW_THREE_HINTS = "3";
        final String SHOW_TWO_HINTS = "2";
        final String SHOW_ONE_HINT = "1";
        final String INVALID_HINT_COUNT_MESSAGE = "You much either choose 1, 2, 3 or Cancel for practice mode";
        final String FIRST_GUESS = "first";
        final String SECOND_GUESS = "second";
        final String THIRD_GUESS = "third";
        final String GUESS_PROMPT = "%s\nPlease enter your %s guess";
        final String DUPLICATED_GUESS_WARNING_MESSAGE = "Don't choose the same number twice";
        final String CORRECT_GUESSES_MESSAGE = "%d numbers were guessed correctly!";
        final String GAME_WON_MESSAGE = "Congratulations!\n%s Won!\nDo you want to start a new game?";

        boolean gameWon, isNumberOfHintsToShowInvalid, isFirstGuess, isSecondGuess, isDuplicateGuess, shouldPlayAgain;
        String player1, player2, numberOfHintsToShow, guessCountString, currentPlayer, currentGuessString, guessPromptMessage;
        int randomNumber1, randomNumber2, randomNumber3, amountOfGuesses, amountOfCorrectGuesses, firstGuess, secondGuess, currentGuess;

        // Game loop
        do {
            // reset the game
            currentPlayer = null;
            gameWon = false;

            // Sign up - asking for the players' names.
            player1 = "Player #1, " + JOptionPane.showInputDialog(String.format(PLAYER_NAME_PROMPT, 1));
            player2 = "Player #2, " + JOptionPane.showInputDialog(String.format(PLAYER_NAME_PROMPT, 2));

            // randomly choose 3 distinct numbers between 1 and RANDOM_LIMIT inclusive
            randomNumber1 = (int) (Math.random() * RANDOM_LIMIT) + 1;
            do {
                randomNumber2 = (int) (Math.random() * RANDOM_LIMIT) + 1;
            }
            while (randomNumber2 == randomNumber1);

            do {
                randomNumber3 = (int) (Math.random() * RANDOM_LIMIT) + 1;
            }
            while (randomNumber3 == randomNumber2 || randomNumber3 == randomNumber1);

            // Practice mode - reveal some of the random numbers according to player's input
            do {
                numberOfHintsToShow = JOptionPane.showInputDialog(PRACTICE_PROMPT);
                // The player chose "Cancel"
                if (numberOfHintsToShow == null){
                    isNumberOfHintsToShowInvalid = false;
                }
                else {
                    switch (numberOfHintsToShow) {
                        case SHOW_THREE_HINTS:
                            System.out.print(randomNumber3 + ", ");
                        case SHOW_TWO_HINTS:
                            System.out.print(randomNumber2 + ", ");
                        case SHOW_ONE_HINT:
                            System.out.println(randomNumber1);
                            isNumberOfHintsToShowInvalid = false;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, INVALID_HINT_COUNT_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
                            isNumberOfHintsToShowInvalid = true;
                    }
                }
            } while (isNumberOfHintsToShowInvalid);

            // player's turn loop
            do {
                // Reset the round
                firstGuess = 0;
                secondGuess = 0;
                amountOfGuesses = 0;
                amountOfCorrectGuesses = 0;

                //Toggle the current player.
                if (currentPlayer == null || currentPlayer.equals(player2)) {
                    currentPlayer = player1;
                } else {
                    currentPlayer = player2;
                }

                // Guessing loop
                while (amountOfGuesses < MAXIMUM_GUESSES_ALLOWED) {

                    isFirstGuess = amountOfGuesses == 0;
                    isSecondGuess = amountOfGuesses == 1;

                    //Ask the player to guess a number
                    if (isFirstGuess){
                        guessCountString = FIRST_GUESS;
                    } else if (isSecondGuess){
                        guessCountString = SECOND_GUESS;
                    } else {
                        guessCountString = THIRD_GUESS;
                    }
                    guessPromptMessage = String.format(GUESS_PROMPT, currentPlayer, guessCountString);
                    currentGuessString = JOptionPane.showInputDialog(guessPromptMessage);
                    currentGuess = Integer.parseInt(currentGuessString);

                    //Verify current guess was not guessed already
                    if (isFirstGuess) {
                        firstGuess = currentGuess;
                        isDuplicateGuess = false;
                    } else if (isSecondGuess) {
                            isDuplicateGuess = currentGuess == firstGuess;
                            if (!isDuplicateGuess) secondGuess = currentGuess;
                    } else {
                        isDuplicateGuess = currentGuess == secondGuess || currentGuess == firstGuess;
                    }

                    if (isDuplicateGuess) {
                        //Error message for duplicated guess
                        JOptionPane.showMessageDialog(null, DUPLICATED_GUESS_WARNING_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        amountOfGuesses++;
                        //Check if the guess is correct
                        if (currentGuess == randomNumber1 || currentGuess == randomNumber2 || currentGuess == randomNumber3) {
                            amountOfCorrectGuesses++;
                        }
                    }
                }
                if (amountOfCorrectGuesses == RANDOM_NUMBERS_COUNT) {
                    // Player guessed all numbers correctly and won the game
                    gameWon = true;
                } else {
                    //Show how many guesses were correct
                    JOptionPane.showMessageDialog(null, String.format(CORRECT_GUESSES_MESSAGE, amountOfCorrectGuesses));
                }
            } while (!gameWon);
            // Ask if the players want to play another game
            shouldPlayAgain = JOptionPane.showConfirmDialog(null, String.format(GAME_WON_MESSAGE, currentPlayer)) == CONFIRM;
        } while (shouldPlayAgain);
    }
}
