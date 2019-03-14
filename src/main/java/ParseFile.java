import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ParseFile {

    private static final Map<Integer, PageNode> map = new HashMap<>();


    public static PageTree parseFile(String filePath) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filePath));
        int pageNumber = 1;
        while(input.hasNextLine()) {
            PageNode page = map.get(pageNumber);
            String line = input.nextLine();
            String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if (map.containsKey(pageNumber)) {
                if (data[0].equals("ENDING")) {
                    map.get(pageNumber).setText(data[1]);
                    map.get(pageNumber).setEnding(true);
                }
                else {
                    map.get(pageNumber).setText(data[data.length - 1]);
                    for (int i = 0; i < data.length - 1; i++) {
                        if (map.containsKey(Integer.valueOf(data[i]))) {
                            page.addChild(map.get(Integer.valueOf(data[i])));
                        } else {
                            PageNode child = new PageNode(Integer.valueOf(data[i]), null, false);
                            page.addChild(child);
                            map.put(Integer.valueOf(data[i]), child);
                        }
                    }
                }

            } else {
                if (data[0].equals("ENDING")) {
                    page = new PageNode(pageNumber, data[1], true);
                } else {
                    page = new PageNode(pageNumber, data[data.length - 1], false);
                    for (int i = 0; i < data.length - 1; i++) {
                        PageNode child = new PageNode(Integer.valueOf(data[i]), null, false);
                        page.addChild(child);
                        map.put(Integer.valueOf(data[i]), child);
                    }
                }
                map.put(pageNumber, page);
            }

            pageNumber++;
        }
        return new PageTree(map.get(1));
    }
}
