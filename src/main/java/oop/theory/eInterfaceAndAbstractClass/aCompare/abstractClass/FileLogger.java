package oop.theory.eInterfaceAndAbstractClass.aCompare.abstractClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;

/**
 * 抽象类的子类：输出日志到文件
 */
public class FileLogger extends Logger {

    private Writer fileWriter;

    public FileLogger(String name, boolean enabled, Level minPermittedLevel, String filePath) {
        super(name, enabled, minPermittedLevel);
        try {
            this.fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doLog(Level level, String message) {
        // 格式化 level 和 message，输出到日志文件
        String result = level + " : " + message;
        try {
            fileWriter.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
