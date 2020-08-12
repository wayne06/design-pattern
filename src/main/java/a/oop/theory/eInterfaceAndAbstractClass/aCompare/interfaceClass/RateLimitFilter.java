package a.oop.theory.eInterfaceAndAbstractClass.aCompare.interfaceClass;

public class RateLimitFilter implements Filter {
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        //...限流逻辑...
    }
}
