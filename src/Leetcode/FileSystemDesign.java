package Leetcode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * You are asked to design a file system that allows you to create new paths and associate them with different values.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
 * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
 *
 * Implement the FileSystem class:
 *
 *     bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true.
 *     Returns false if the path already exists or its parent path doesn't exist.
 *
 *     int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 *
 * Example 1:
 *
 * Input:
 * ["FileSystem","createPath","get"]
 * [[],["/a",1],["/a"]]
 * Output:
 * [null,true,1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/a", 1); // return true
 * fileSystem.get("/a"); // return 1
 *
 * Example 2:
 *
 * Input:
 * ["FileSystem","createPath","createPath","get","createPath","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 * [null,true,true,2,false,-1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/leet", 1); // return true
 * fileSystem.createPath("/leet/code", 2); // return true
 * fileSystem.get("/leet/code"); // return 2
 * fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 * fileSystem.get("/c"); // return -1 because this path doesn't exist.
 *
 *
 *
 * Constraints:
 *
 *     The number of calls to the two functions is less than or equal to 104 in total.
 *     2 <= path.length <= 100
 *     1 <= value <= 109
 */

public class FileSystemDesign {
    private Map<String, Integer> filepath;

    FileSystemDesign() {
        filepath = new HashMap<>();
        filepath.put("", -1);
    }

    public boolean createPath(String path, int value) {
        // Get the parent of the path to check for its existence
        int idx = path.lastIndexOf("/");
        String parent = path.substring(0, idx);

        if(!filepath.containsKey(parent)) {
            // parent path does not exist.
            return false;
        }

        int val = filepath.getOrDefault(path, -1);
        if(val == -1) {
            // current path does not exist.
            filepath.put(path, value);
        }
        return val == -1; // will be -1 if the path does not exist, else would return false.
    }

    public int get(String path) {
        return filepath.getOrDefault(path, -1);
    }

    public static void main(String[] args) {
        FileSystemDesign obj = new FileSystemDesign();
        System.out.println(obj.createPath("/a", 1));
        System.out.println(obj.get("/a"));
    }
}
