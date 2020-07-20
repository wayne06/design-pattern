package designpattern.behavioral.chainofresponsibility.demo4;

public class ApplicationDemo {

    public static void main(String[] args) {

        SensitiveWordFilterChain chain = new SensitiveWordFilterChain();
        chain.addFilter(new AdsWordFilter());
        chain.addFilter(new SexyWordFilter());
        chain.addFilter(new PoliticalWordFilter());

        boolean legal = chain.filter(new Content());
        if (!legal) {
            // 不能发表
        } else {
            // 发表
        }

    }

}
