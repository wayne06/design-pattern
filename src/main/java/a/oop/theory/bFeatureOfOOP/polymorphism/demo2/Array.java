package a.oop.theory.bFeatureOfOOP.polymorphism.demo2;

public class Array implements Iterator {

    private String[] data;

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public String remove() {
        return null;
    }
}
