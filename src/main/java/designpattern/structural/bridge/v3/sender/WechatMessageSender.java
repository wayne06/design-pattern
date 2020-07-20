package designpattern.structural.bridge.v3.sender;

import java.util.List;

public class WechatMessageSender implements MessageSender {

    private List<String> wechats;

    public WechatMessageSender(List<String> wechats) {
        this.wechats = wechats;
    }

    public WechatMessageSender() {
    }

    @Override
    public void send(String message) {

    }
}
