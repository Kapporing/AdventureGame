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
        choices.add(pageCurrently);
        playGame(input.next());
    }

    public void truncateChoices(int pageNumber) { }

    public LinkedList<PageNode> getChoices() {
        return null;
    }
}
