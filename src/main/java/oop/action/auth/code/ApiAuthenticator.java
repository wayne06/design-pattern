package oop.action.auth.code;

/**
 * 接口鉴权并不是一个独立运行的系统，而是一个集成在系统上运行的组件，
 * 所以，我们封装所有的实现细节，设计了一个最顶层的 ApiAuthenticator 接口类，暴露一组给外部调用者使用的 API 接口，
 * 作为触发执行鉴权逻辑的入口
 */
public interface ApiAuthenticator {

    void auth(String url);

    void auth(ApiRequest apiRequest);

}
