package d.designpattern.structural.composite.demo2;

import java.io.File;

public class MyFile extends FileSystemNode {

    public MyFile(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        File file = new File(path);
        if (!file.exists()) {
            return 0;
        }
        return file.length();
    }
}
