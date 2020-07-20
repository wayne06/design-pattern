package designpattern.behavioral.command.demo1;

public class Request {

    private Event event;

    public Request(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
