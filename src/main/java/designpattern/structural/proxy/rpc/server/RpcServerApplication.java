package designpattern.structural.proxy.rpc.server;

import designpattern.structural.proxy.rpc.RpcServer;

import java.io.IOException;

/**
 * @author wayne
 */
public class RpcServerApplication {

    public static void main(String[] args) throws IOException {
        CalculatorService service = new CalculatorServiceImpl();
        RpcServer server = new RpcServer();
        server.export(service, 1234);
    }

}
