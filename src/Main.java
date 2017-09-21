/**
 * @author Jonathan Barnett
 * @version 1.0
 *
 * This is a basic hangman game with only movies for words. A user
 * guesses one letter at a time, if the letter is in the word, the
 * letter shows up as being guessed. If incorrect the user gets an
 * incorrect guess. If the user gets too many incorrect guesses before
 * guessing correctly, they lose.
 */
public class Main {

    public static void main(String[] args) {
        GuessTheMovie gtm = new GuessTheMovie();
        gtm.start();
    }

}