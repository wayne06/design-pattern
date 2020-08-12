package a.oop.theory.fProgramToAnInterface.action.v2;

import java.awt.*;

/**
 * 上传下载流程改变：私有云不需要支持access token
 */
public class PrivateImageStore implements ImageStore {
    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExist(bucketName);
        //...上传图片到阿里云...
        //...返回图片在阿里云上的地址...
        return null;
    }

    @Override
    public Image download(String url) {
        return null;
    }

    private void createBucketIfNotExist(String bucketName) {
        //...创建 bucket...
        //...失败会抛出异常...
    }

}
