package designpattern.behavioral.template.demo2;

import java.io.IOException;

public class ByteArrayInputStream extends MyInputStream {

    @Override
    public synchronized int read() throws IOException {
        return 0;
    }

}
