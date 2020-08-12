package d.designpattern.structural.bridge.v3.sender;

import java.util.List;

public class TelephoneMessageSender implements MessageSender {

    private List<String> telephones;

    public TelephoneMessageSender() {
    }

    public TelephoneMessageSender(List<String> telephones) {
        this.telephones = telephones;
    }

    @Override
    public void send(String message) {

    }
}
