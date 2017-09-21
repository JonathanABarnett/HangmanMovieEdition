import java.util.*;

public class GuessTheMovie {

    private String movie;
    private char[] charMovie;
    private int numberOfGuesses = 0;
    private int maxGuess = 10;
    private ArrayList<String> guessedLetters = new ArrayList<>();
    private Movies movies = new Movies();
    private Scanner reader = new Scanner(System.in);
    private char[] displayWord;
    private boolean cont = true;

    /**
     * Initializes the game by randomly selecting a movie and pushing it to a char[]
     * the game will continue until the user has guessed the correct answer or used up
     * the max number of wrong guesses.
     */
    public void start() {
        movie = movies.getMovie();
        charMovie = stringToArray(movie.toLowerCase());
        //System.out.println(movie);
        initializeWordGuess();
        while (cont) {
            if (!didWin()) {
                if (checkNumOfGuesses()) {
                    System.out.println("What is your guess?");
                    getGuess();
                    if (guessedLetters != null) {
                        Collections.sort(guessedLetters);
                    }
                    System.out.println("Letters guessed: " + guessedLetters);
                } else {
                    System.out.println("You lose!");
                    System.out.println("The correct answer was: " + movie);
                    break;
                }
            } else {
                System.out.println("You Win!");
                System.out.println("The correct answer is: " + movie);
                break;
            }
        }
    }

    /**
     * Checks to see if the {@link #numberOfGuesses} is less than {@link #maxGuess}
     *
     * @return boolean of {@link #numberOfGuesses} < {@link #maxGuess}
     */
    private boolean checkNumOfGuesses() {
        if (numberOfGuesses < maxGuess) {
            return true;
        } else {
            cont = false;
            return false;
        }
    }

    /**
     * Checks to see if the user has guessed all the correct letters in the movie title.
     *
     * @return boolean whether {@link #charMovie} == {@link #displayWord}
     */
    private boolean didWin() {
        if (Arrays.equals(charMovie, displayWord)) {
            cont = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Converts the movieName to a char[]
     *
     * @param movieName randomly selected movie
     * @return the movieName in char[]
     */
    private char[] stringToArray(String movieName) {
        char[] charMovie = new char[movieName.length()];
        for (int i = 0; i < movieName.length(); i++) {
            charMovie[i] = movieName.charAt(i);
        }
        return charMovie;
    }

    /**
     * Gets the user's input and verifies that meets the required parameters of only
     * being 1 letter long, making sure it hasn't already been guessed yet, and that
     * it isn't an empty guess. If all requirements are met the letter is added to the
     * {@link #guessedLetters} and the {@link #isLetterInWord(String)} is invoked.
     *
     * @return guess which is the letter that user guessed
     */
    private String getGuess() {
        boolean continueGetGuess = true;
        String guess = null;
        while (continueGetGuess) {
            guess = reader.nextLine().toLowerCase();
            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed " + guess + ". Guess again.");
            } else if (guess.equals(" ")) {
                System.out.println("Please type a letter.");
            } else if (guess.length() > 1 || guess.length() < 1) {
                System.out.println("Please only type one letter.");
            } else {
                guessedLetters.add(guess);
                continueGetGuess = false;
            }
        }
        isLetterInWord(guess);
        return guess;
    }

    /**
     * Checks to see if the letter guessed is in the movie title by going through each
     * letter in the movie title, if the letter is in the movie title it will replace '_'
     * with the letter guessed. This occurs for each instance of the letter in the movie.
     * title. If the letter is not in the movie title it will increase the numberOfGuesses
     * and print out how many times the user has incorrectly guessed.
     *
     * @param letterGuessed the user's input from {@link #getGuess()}
     */
    private void isLetterInWord(String letterGuessed) {
        if (movie.toLowerCase().contains(letterGuessed)) {
            for (int i = 0; i < movie.length(); i++) {
                if (letterGuessed.charAt(0) == movie.toLowerCase().charAt(i)) {
                    displayWord[i] = letterGuessed.charAt(0);
                }
            }
        } else {
            numberOfGuesses++;
        }
        System.out.println(displayWord);
        System.out.println("You have guessed incorrectly " + numberOfGuesses + " times so far.");
    }

    /**
     * Initializes the board by replacing 1 '_' for each letter in the movie title. If
     * there is a space in the title, the board will correctly display where the spaces
     * correlate with the spaces in the movie title. This is stored in the
     * {@link #displayWord} array.
     */
    private void initializeWordGuess() {
        displayWord = new char[movie.length()];
        for (int i = 0; i < movie.length(); i++) {
            if (movie.charAt(i) != ' ') {
                displayWord[i] = '_';
            } else {
                displayWord[i] = ' ';
            }
        }
        System.out.println(displayWord);
        System.out.println();
    }

}