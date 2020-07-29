package oop.theory.fProgramToAnInterface.action.v1;

import java.awt.*;

public class ImageProcessingJob {

    private static final String BUCKET_NAME = "ai_images_bucket";

    public void process() {
        //处理图片，并封装为 Image 对象
        Image image = null;
        AliyunImageStore imageStore = new AliyunImageStore();
        imageStore.createBucketIfNotExist(BUCKET_NAME);
        String accessToken = imageStore.generateAccessToken();
        imageStore.uploadToAliyun(image, BUCKET_NAME, accessToken);

    }
}
