package a.oop.theory.eInterfaceAndAbstractClass.aCompare.interfaceClass;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private List<Filter> filters = new ArrayList<>();

    public void handleRpcRequest(RpcRequest request) {
        filters.add(new AuthenticationFilter());
        filters.add(new RateLimitFilter());
        for (Filter filter : filters) {
            try {
                filter.doFilter(request);
            } catch (RpcException e) {
                //...处理过滤结果...
                e.printStackTrace();
            }
            //...省略其他处理逻辑...
        }
    }

}
