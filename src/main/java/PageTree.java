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

    public PageNode getRoot() {
        return this.root;
    }

    public PageNode getNode(int pageNumber) {
        if (this.root == null) {
            return null;
        }
        Queue<PageNode> que = new LinkedList<>();
        que.add(this.root);
        while(!que.isEmpty()) {
            PageNode current = que.remove();
            if (current.getPageNumber() == pageNumber) {
                return current;
            }
            que.addAll(current.getChildren());
        }
        return null;
    }

    public List<PageNode> cheat(int desiredEnding) {
        LinkedList<PageNode> way = new LinkedList<>();
        way.add(this.root);
        return cheat(desiredEnding, way, this.root);
    }

    private List<PageNode> cheat(int desiredEnding, List<PageNode> ways, PageNode page) {
        if (page.getChildren() != null) {
            for (PageNode child : page.getChildren()) {
                List<PageNode> path = new LinkedList<>(ways);
                path.add(child);
                List<PageNode> possibleWay = cheat(desiredEnding, path, child);
                if (possibleWay != null) {
                    return possibleWay;
                }
            }
        }
        if (page.getPageNumber() == desiredEnding) {
            return ways;
        }
        return null;
    }

}