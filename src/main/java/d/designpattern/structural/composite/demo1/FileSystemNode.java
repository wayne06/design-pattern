package d.designpattern.structural.composite.demo1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 需求：
 *
 * 设计一个类来表示文件系统中的目录，能方便地实现下面这些功能：
 * 1. 动态地添加、删除某个目录下的子目录或文件；
 * 2. 统计指定目录下的文件个数；
 * 3. 统计指定目录下的文件总大小。
 *
 * 骨架代码如下：把文件和目录统一用 FileSystemNode 类来表示，并且通过 isFile 属性来区分
 *
 * countNumOfFiles() 和 countSizeOfFiles() 这两个函数，实际上这就是树上的递归遍历算法。
 * 对于文件，直接返回文件的个数（返回 1）或大小。
 * 对于目录，遍历目录中每个子目录或者文件，递归计算它们的个数或大小，然后求和，就是这个目录下的文件个数和文件大小。
 */
public class FileSystemNode {

    private String path;
    private boolean isFile;
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

    public int countNumOfFiles() {
        if (isFile) {
            return 1;
        }
        int numOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            numOfFiles += fileOrDir.countNumOfFiles();
        }
        return numOfFiles;
    }

    public long countSizeOfFiles() {
        if (isFile) {
            File file = new File(path);
            if (!file.exists()) {
                return 0;
            }
            return file.length();
        }
        long sizeOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            sizeOfFiles += fileOrDir.countSizeOfFiles();
        }
        return sizeOfFiles;
    }

    public String getPath() {
        return path;
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public void removeSubNode(FileSystemNode fileOrDir) {
        int size = subNodes.size();
        int i = 0;
        for (; i < size; i++) {
            if (subNodes.get(i).getPath().equalsIgnoreCase(fileOrDir.getPath())) {
                break;
            }
        }
        if (i < size) {
            subNodes.remove(i);
        }
    }

}
