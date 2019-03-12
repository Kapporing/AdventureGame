import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Driver {
    private Playthrough playthrough;
    private PageTree book;

    public void play() throws FileNotFoundException {
        book = ParseFile.parseFile("src/main/resources/book1.csv");
        playthrough = new Playthrough(book);
        playthrough.playGame();

        System.out.println("You finished the story!");
        showPath();
    }

    public void playFrom(int pageNumber) {
        playthrough.truncateChoices(pageNumber);
        playthrough.playGame(String.valueOf(pageNumber));
    }

    public void showPath() {
        System.out.println("Your path:");
        System.out.println(playthrough.getChoices().stream().map(page ->
                String.valueOf(page.getPageNumber())
        ).collect(Collectors.joining("->")));
    }

    public void cheat(int desiredEnding) {
        List<PageNode> path = book.cheat(desiredEnding);
        System.out.println("A way to get there would be:");
        System.out.println(path.stream().map(page ->
                String.valueOf(page.getPageNumber())
        ).collect(Collectors.joining("->")));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Driver driver = new Driver();
        driver.play();

        String choice;
        do {
            System.out.println(
                    "What would you like to do next? " +
                            "Choose one of [quit, play_again, play_from $page_number, cheat $ending_page_number"
            );
            choice = new Scanner(System.in).nextLine();

            if (choice.equals("play_again")) {
                driver.play();
            } else if (choice.startsWith("play_from")) {
                int pageNumber = Integer.valueOf(choice.split(" ")[1].trim());
                driver.playFrom(pageNumber);

            } else if (choice.startsWith("cheat")) {
                int pageNumber = Integer.valueOf(choice.split(" ")[1].trim());
                driver.cheat(pageNumber);
            }

        } while (!choice.equals("quit"));
    }
}
