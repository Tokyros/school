//Shahar Rosen - 2045417917
package com.company;

import javax.swing.JOptionPane;

public class HW1_ShaharRosen {

    public static void main(String[] args) {
        // Upper bound for random numbers (including).
        final int RANDOM_LIMIT = 5;
        // Result when user selects confirm in a confirmation dialog.
        final int CONFIRM = 0;
        // Maximum times a user is allowed to guess a number.
        final int MAXIMUM_GUESSES_ALLOWED = 3;
        // Total number of random numbers to guess.
        final int RANDOM_NUMBERS_COUNT = 3;
        // Message to show when asking the user for a user name.
        final String PLAYER_NAME_PROMPT = "Please enter full name for player #%s:";
        // Message to show when asking if the user wants practice mode.
        final String PRACTICE_PROMPT = "For Practice:\nHow many secret numbers do you want to show:\nPress 1, 2, 3 or Cancel for none";
        final String INVALID_HINT_COUNT_MESSAGE = "You much either choose 1, 2, 3 or Cancel for practice mode";
        final String FIRST_GUESS = "First";
        final String SECOND_GUESS = "Second";
        final String THIRD_GUESS = "Third";

        final String GUESS_PROMPT = "%s\nPlease enter your %s guess";
        final String DUPLICATED_GUESS_WARNING_MESSAGE = "Don't choose the same number twice";
        // Message to show when the game is won.
        final String GAME_WON_MESSAGE = "Congratulations!\n%s Won!\nDo you want to start a new game?";

        // Flag to check weather the user request for practice mode was valid
        boolean hintCountValid;
        // Holds the choice of weather to play another game or not.
        boolean shouldPlayAgain;
        // Strings to hold the players' names and the requested number of hints in practice mode.
        String player1, player2, hintCount, guessCountString;
        // Variables to hold the random numbers to guess.
        int randomNumber1, randomNumber2, randomNumber3;

        do {
            //Ask the first player's name
            player1 = "Player #1, " + JOptionPane.showInputDialog(String.format(PLAYER_NAME_PROMPT, 1));
            //Ask the second player's name
            player2 = "Player #2, " + JOptionPane.showInputDialog(String.format(PLAYER_NAME_PROMPT, 2));

            // We get the first random number, this one is always ok
            // We add one so it's never zero and multiply by the upper bound of our random numbers
            randomNumber1 = (int) (Math.random() * RANDOM_LIMIT) + 1;

            do {
                randomNumber2 = (int) (Math.random() * RANDOM_LIMIT) + 1;
            }
            // for the second random number we make sure it is not the same as the first one
            while (randomNumber2 == randomNumber1);

            do {
                randomNumber3 = (int) (Math.random() * RANDOM_LIMIT) + 1;
            }
            // for the thirds random number we make sure it is different from the first and the second number.
            while (randomNumber3 == randomNumber2 || randomNumber3 == randomNumber1);

            do {
                //Ask the user how many hints he wants to get
                hintCount = JOptionPane.showInputDialog(PRACTICE_PROMPT);
                //Print to the console the requested amount of hints
                if (hintCount == null){
                    hintCountValid = true;
                }
                else {
                    switch (hintCount) {
                        case "3":
                            System.out.print(randomNumber3 + ", ");
                        case "2":
                            System.out.print(randomNumber2 + ", ");
                        case "1":
                            System.out.println(randomNumber1);
                            hintCountValid = true;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, INVALID_HINT_COUNT_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
                            hintCountValid = false;
                    }
                }
            } while (!hintCountValid);

            //Initialize the player to player2, that way when the game starts, player1 will be the first to go.
            String currentPlayer = player2;

            //Flag to keep track if a player won the game, used to stop the game and announce the winner.
            boolean gameWon = false;
            do {
                //Toggle the current player.
                if (currentPlayer.equals(player1)) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
                }

                //Variables to keep track of the current user's guesses
                int guess1 = 0;
                int guess2 = 0;

                //Variables to keep track of how many guesses, out of which how many are correct
                int numOfGuesses = 0;
                int correctGuesses = 0;

                //Make sure the users don't get more guesses than the maximum allowed
                while (numOfGuesses < MAXIMUM_GUESSES_ALLOWED) {

                    guessCountString = FIRST_GUESS;
                    switch (numOfGuesses){
                        case 1:
                            guessCountString = SECOND_GUESS;
                            break;
                        case 2:
                            guessCountString = THIRD_GUESS;
                            break;
                    }

                    //A flag to keep track of weather the user guessed the same number twice.
                    boolean duplicateGuessFlag = false;
                    //Get the current guess from the user
                    int currentGuess = Integer.parseInt(JOptionPane.showInputDialog(String.format(GUESS_PROMPT, currentPlayer, guessCountString)));
                    // First guess
                    if (numOfGuesses == 0) {
                        //The first guess is always ok because it is still not possible to repeat guesses
                        guess1 = currentGuess;
                    // Second guess
                    } else if (numOfGuesses == 1) {
                        guess2 = currentGuess;
                        // Raise the flag if the second guess is the same as the first one.
                        if (currentGuess == guess1) {
                            duplicateGuessFlag = true;
                        }
                    // Third guess
                    } else {
                        // Raise the flag if the third guess is the same as the first or second guesses
                        if (currentGuess == guess2 || currentGuess == guess1) {
                            duplicateGuessFlag = true;
                        }
                    }

                    // If a user guessed the same number twice, we raise a warning to the user.
                    if (duplicateGuessFlag) {
                        JOptionPane.showMessageDialog(null, DUPLICATED_GUESS_WARNING_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        // If the user did not repeat a guess, we consider this attempt valid and increment the number of guesses.
                        numOfGuesses++;
                        // If the guess is equal to any of the random numbers increment the number of correct guesses
                        if (currentGuess == randomNumber1 || currentGuess == randomNumber2 || currentGuess == randomNumber3) {
                            correctGuesses++;
                        }
                    }
                }
                // If the user guessed all three random numbers right, we raise the gameWon flag
                if (correctGuesses == RANDOM_NUMBERS_COUNT) {
                    gameWon = true;
                }
            //If the player did not guess all three numbers correctly, we start the loop again, turning over to the other player.
            } while (!gameWon);
            // After the game is won we ask if the game should play another round
            shouldPlayAgain = JOptionPane.showConfirmDialog(null, String.format(GAME_WON_MESSAGE, currentPlayer)) == CONFIRM;
        //If the user doesn't confirm another round, the program quits.
        } while (shouldPlayAgain);
    }
}
