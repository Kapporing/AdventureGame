import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PageTree {
    private PageNode root;

    PageTree() {
        this(null);
    }

    PageTree(PageNode rootData) {
        this.root = new PageNode(rootData);
    }

    public PageNode getRoot() {
        return this.root;
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

    public List<PageNode> cheat(int desiredEnding) {
        return cheat(desiredEnding, new LinkedList<>(), this.root);
    }

    private List<PageNode> cheat(int desiredEnding, List<PageNode> ways, PageNode page) {
        if (page.getChildren() != null) {
            for (PageNode child : page.getChildren()) {
                List<PageNode> path = new LinkedList<>(ways);
                path.add(child);
                cheat(desiredEnding, path, child);
                if (page.getPageNumber() == desiredEnding) {
                    return ways;
                }
            }
        }
        return null;
    }

}