package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ParseFile {

    private static final Map<Integer, PageNode> map = new HashMap<Integer, PageNode>();


    public static PageTree parseFile(String filePath) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filePath));
        int pageNumber = 1;
//        PageTree tree = new PageTree(new PageNode(pageNumber, "", false));
        while(input.hasNextLine()) {
            PageNode page = null;
            String line = input.nextLine();
            String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if (map.containsKey(pageNumber)) {
                if (data[0].equals("ENDING")) {
                    map.get(pageNumber).setText(data[1]);
                }
                else {
                    map.get(pageNumber).setText(data[data.length - 1]);
                }

            } else {
                if (data[0].equals("ENDING")) {
                    page = new PageNode(pageNumber, data[1], true);
                } else {
                    for (int i = 0; i < data.length; i++) {
                        if (i == data.length - 1) {
                            page = new PageNode(pageNumber, data[i], false);
                        } else {
                            map.put(Integer.valueOf(data[i]), new PageNode(Integer.valueOf(data[i]), null, false));
                        }
                    }
                }
                map.put(pageNumber, page);
            }

            pageNumber++;
        }

        return null;
    }
}
