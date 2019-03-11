import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PageTree {
    private PageNode root;

    PageTree() {
        this(null);
    }

    PageTree(PageNode rootData) {
        this.root = new PageNode(rootData);
    }

    public PageNode getNode(int pageNumber) {
        if (this.root == null) {
            return null;
        }
        Queue<PageNode> que = new LinkedList<>();
        que.add(this.root);
        while (!que.isEmpty()) {
            PageNode current = que.remove();
            if (current.getPageNumber() == pageNumber) {
                return current;
            }
            que.addAll(current.getChildren());
        }
        return null;
    }

    public List<PageNode> cheat(int desiredEnding) { return null; }

}