package d.designpattern.structural.proxy.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wayne
 */
public class RpcServer {

    public void export(Object service, int port) throws IOException {
        if (service == null) {
            throw new IllegalArgumentException("service instance == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }

        ServerSocket server = new ServerSocket(port);
        while (true) {
            final Socket socket= server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                        String methodName = inputStream.readUTF();
                        try {
                            Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                            Object[] arguments = (Object[]) inputStream.readObject();
                            Method method = service.getClass().getMethod(methodName, parameterTypes);
                            Object result = method.invoke(service, arguments);
                            outputStream.writeObject(result);
                        } catch (Throwable t) {
                            outputStream.writeObject(t);
                        } finally {
                            outputStream.close();
                            inputStream.close();
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
