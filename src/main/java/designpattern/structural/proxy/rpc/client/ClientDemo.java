package designpattern.structural.proxy.rpc.client;

import designpattern.structural.proxy.rpc.RpcClient;
import designpattern.structural.proxy.rpc.server.CalculatorService;

/**
 * @author wayne
 */
public class ClientDemo {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalculatorService service = client.refer(CalculatorService.class, "127.0.0.1", 1234);
        int result = service.add(2, 4);
        System.out.println("result: " + result);
    }

}
