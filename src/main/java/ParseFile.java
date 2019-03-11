import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ParseFile {

    private static final Map<Integer, PageNode> map = new HashMap<Integer, PageNode>();


    public static PageTree parseFile(String filePath) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filePath));
        PageTree tree = new PageTree();
        int pageNumber = 1;
        while(input.hasNextLine()) {
            PageNode page;
            String line = input.nextLine();
            String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if (map.containsKey(pageNumber)) {
                if (data[0].equals("ENDING")) {

                }


            } else {
                if (data[0].equals("ENDING")) {
                    page = new PageNode(pageNumber, data[1], true);
                } else {
                    page = new PageNode(pageNumber, data[2], false);
                }
                map.put(pageNumber, page);
            }

        }



        return tree;
    }
}
