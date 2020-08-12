package a.oop.theory.fProgramToAnInterface.action.v2;

import java.awt.*;

public class AliyunImageStore implements ImageStore {

    //...省略属性、构造函数等...

    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExist(bucketName);
        String accessToken = generateAccessToken();
        //...上传图片到阿里云...
        //...返回图片在阿里云上的地址...
        return "url";
    }

    @Override
    public Image download(String url) {
        //...从阿里云下载图片...
        String accessToken = generateAccessToken();
        return null;
    }

    private String generateAccessToken() {
        //...根据 accessKey/secrectKey 等生成 access token
        return null;
    }

    private void createBucketIfNotExist(String bucketName) {
        //...创建 bucket...
        //...失败会抛出异常...
    }

}
