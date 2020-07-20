package designpattern.behavioral.strategy.demo4;

public class SortingTool {

    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        sorter.sortFile(args[0]);
    }

}
