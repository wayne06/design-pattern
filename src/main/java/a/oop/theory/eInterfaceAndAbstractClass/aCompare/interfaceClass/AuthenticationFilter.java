package a.oop.theory.eInterfaceAndAbstractClass.aCompare.interfaceClass;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(RpcRequest req) throws RpcException {
        //...鉴权逻辑...
    }
}
