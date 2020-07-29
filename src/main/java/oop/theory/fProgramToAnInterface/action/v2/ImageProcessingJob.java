package oop.theory.fProgramToAnInterface.action.v2;

import java.awt.*;

/**
 * 多人在定义接口的时候，希望通过实现类来反推接口的定义。先把实现类写好，然后看实现类中有哪些方法，照抄到接口定义中。
 * 如果按照这种思考方式，就有可能导致接口定义不够抽象，依赖具体的实现。这样的接口设计就没有意义了。
 * 不过，如果你觉得这种思考方式更加顺畅，那也没问题，只是将实现类的方法搬移到接口定义中的时候，要有选择性的搬移，
 * 不要将跟具体实现相关的方法搬移到接口中，比如 AliyunImageStore 中的 generateAccessToken() 方法。
 *
 * 总结：在做软件开发的时候，一定要有抽象意识、封装意识、接口意识。
 * 在定义接口的时候，不要暴露任何实现细节。接口的定义只表明做什么，而不是怎么做。
 * 而且在设计接口的时候，要多思考这样的接口设计是否足够通用，是否能够做到在替换具体的接口实现的时候，不需要任何接口定义的改动。
 *
 * 是否需要为每个类定义接口？
 *
 * 这条原则的设计初衷是，将接口和实现相分离，封装不稳定的实现，暴露稳定的接口。
 * 上游系统面向接口而非实现编程，不依赖不稳定的实现细节，
 * 这样当实现发生变化的时候，上游系统的代码基本上不需要做改动，以此来降低代码间的耦合性，提高代码的扩展性。
 *
 * 从这个设计初衷上来看，如果在我们的业务场景中，某个功能只有一种实现方式，未来也不可能被其他实现方式替换，
 * 那我们就没有必要为其设计接口，也没有必要基于接口编程，直接使用实现类就可以了。
 *
 *
 */
public class ImageProcessingJob {

    private static final String BUCKET_NAME = "ai_images_bucket";

    public void process() {
        // 处理图片，并封装为 Image 对象
        Image image = null;
        ImageStore imageStore = new PrivateImageStore();
        imageStore.upload(image, BUCKET_NAME);
    }

}
