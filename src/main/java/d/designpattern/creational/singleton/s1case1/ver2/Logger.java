package d.designpattern.creational.singleton.s1case1.ver2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private FileWriter writer;

    public Logger() {
        File file = new File("/Users/xx/log.txt");
        try {
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) throws IOException {
        synchronized (this) {
            writer.write(message);
        }
    }
}
