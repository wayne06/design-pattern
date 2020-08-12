package d.designpattern.creational.singleton.s11multiple;

import java.util.concurrent.ConcurrentHashMap;

public class Logger {

    private static final ConcurrentHashMap<String, Logger> instances = new ConcurrentHashMap<>();

    private Logger() {
    }

    public static Logger getInstance(String loggerName) {
        instances.putIfAbsent(loggerName, new Logger());
        return instances.get(loggerName);
    }

    public void log() {

    }
}
