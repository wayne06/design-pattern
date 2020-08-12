package f.inaction.metrics.ver3;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailViewer implements StatViewer {

    private JavaMailSender mailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.mailSender = new JavaMailSenderImpl();
    }

    public EmailViewer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMilliss, long endTimeInMillis) {
        // format the request stats to HTML style
        // send it to email toAddresss.
    }
}
