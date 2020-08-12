package d.designpattern.behavioral.memento.demo2;

public class Snapshot {

    private String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
