package d.designpattern.structural.bridge.v3.sender;

import java.util.List;

/**
 * @author wayne
 */
public class EmailMessageSender implements MessageSender {

    private List<String> emails;

    public EmailMessageSender(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public void send(String message) {

    }
}
