package d.designpattern.creational.builder;


import d.designpattern.creational.builder.b1v1.ResourcePoolConfig;

public class Test {

    public static void main(String[] args) {

        ResourcePoolConfig config1 =
                new ResourcePoolConfig("v1", 16, 10, 12);

        d.designpattern.creational.builder.b1v2.ResourcePoolConfig config2 =
                new d.designpattern.creational.builder.b1v2.ResourcePoolConfig("v1");
        config2.setMaxTotal(16);
        config2.setMaxIdle(10);
        config2.setMinIdle(12);

        d.designpattern.creational.builder.b1v3.ResourcePoolConfig config3 =
                new d.designpattern.creational.builder.b1v3.ResourcePoolConfig.Builder()
                .setName("v3")
                .setMaxTotal(16)
                .setMaxIdle(10)
                .setMinIdle(12)
                .build();

    }



}
