
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Movies {

    private String[] moviesArray;
    private Random random = new Random();
    private String fileName = "src\\movielist.txt";

    /**
     * Use external .txt file with list of movies (to be customized as wanted) that
     * are added to a list and then put into a String array.
     */
    private void getMovieList() {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            List<String> movieList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                movieList.add(scanner.nextLine());
            }
            moviesArray = movieList.toArray(new String[movieList.size()]);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Selects a random movie from the list of provided movies and returns it to be
     * used for {@link GuessTheMovie#start()}
     *
     * @return selectedMovie String
     */
    public String getMovie() {
        try {
            getMovieList();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return moviesArray[random.nextInt(moviesArray.length)];
    }

}