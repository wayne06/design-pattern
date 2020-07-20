package designpattern.behavioral.memento.demo1;

public class InputText {

    private StringBuilder text = new StringBuilder();

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
    }

    public void setText(String text) {
        this.text.replace(0, this.text.length(), text);
    }

    @Override
    public String toString() {
        return text.toString();
    }
}
