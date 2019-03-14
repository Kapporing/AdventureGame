import java.util.HashSet;
import java.util.Set;

public class PageNode {
    private int pageNumber;
    private String text;
    private boolean isEnding;
    private Set<PageNode> children;

    public PageNode(int pageNumber, String text, boolean isEnding) {
        this.pageNumber = pageNumber;
        this.text = text;
        this.isEnding = isEnding;
        this.children = new HashSet<>();
    }


    public PageNode(PageNode node) {
        this.pageNumber = node.getPageNumber();
        this.isEnding = node.isEnding();
        this.text = node.getText();
        this.children = node.getChildren();
    }

    public void setEnding(boolean ending) {
        isEnding = ending;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public Set<PageNode> getChildren() {
        return children;
    }

    public boolean isEnding() {
        return isEnding;
    }
    public String getText() {
        return this.text;
    }

    public void addChild(PageNode child) {
        children.add(child);
    }

}
