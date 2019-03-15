import java.util.*;

public class Playthrough {
    private LinkedList<PageNode> choices = new LinkedList<>();
    private PageTree book;
    private Scanner input = new Scanner(System.in);

    public Playthrough(PageTree book) {
        this.book = book;
    }

    public void playGame() {
        playGame("1");
    }

    public void playGame(String option) {
        PageNode pageCurrently = book.getNode(Integer.valueOf(option));
        Set<PageNode> possibleChild = pageCurrently.getChildren();
        System.out.println(pageCurrently.getText());
        if (pageCurrently.isEnding()) {
            choices.add(pageCurrently);
            return;
        }
        System.out.print("Choose an option: ");
        for (PageNode child : pageCurrently.getChildren()) {
            System.out.print("(" + child.getPageNumber() + ") ");
        }
        System.out.println();
        int opt = 0;
        boolean isNumber = false;
        while(!isNumber) {
            try { // Catches InputMismatchException if option is not an integer (i.e user inputs a string, etc)
                opt = input.nextInt();
                isNumber = true;
            } catch (InputMismatchException ime) {
                System.out.println("That's not a valid input type, try again with an option.");
                input.next();
            }
        }
        if (pageCurrently == null || !possibleChild.contains(book.getNode(Integer.valueOf(opt)))) {
            System.out.println("That's not a valid option, try again");
            playGame(String.valueOf(pageCurrently.getPageNumber()));
            return;
        }
        choices.add(pageCurrently);
        playGame(String.valueOf(opt));
    }

    public void truncateChoices(int pageNumber) {
        LinkedList<PageNode> updatedList = new LinkedList<>();
        for(PageNode page : choices) {
            updatedList.add(page);
            if (page.getPageNumber() == pageNumber) {
                break;
            }
        }
        this.choices = updatedList;
    }

    public LinkedList<PageNode> getChoices() {
        return this.choices;
    }
}
