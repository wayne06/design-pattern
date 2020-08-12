package a.oop.theory.fProgramToAnInterface.action.v1;

import java.awt.*;

/**
 * 假设系统中有很多涉及图片处理和存储的业务逻辑。图片经过处理之后被上传到阿里云上。
 * 为了代码复用，我们封装了图片存储相关的代码逻辑，提供了一个统一的 AliyunImageStore 类，供整个系统来使用。具体的代码实现如下所示：
 *
 * 上传流程包含三个步骤：创建 bucket（可理解为存储目录）、生成 access token、携带 access token 上传图片到指定的 bucket 中
 *
 * 过了一段时间后，我们自建了私有云，不再将图片存储到阿里云了，而是将图片存储到自建私有云上。为了满足该需求的变化，该如何修改代码？
 *
 * 方案一：重新设计实现一个存储图片到私有云的 PrivateImageStore 类，并用它替换掉项目中所有的 AliyunImageStore 类对象
 * 存在的问题：
 * 1.AliyunImageStore 类中有些函数命名暴露了实现细节
 * 2.将图片存储到阿里云的流程，跟存储到私有云的流程，可能并不是完全一致的
 *
 * 方案二：遵循"基于接口而非实现编程"，见 demo2
 * 1.函数的命名不能暴露任何实现细节。
 *   如前面提到的 uploadToAliyun() 就不符合要求，应该改为去掉 aliyun 这样的字眼，改为更加抽象的命名方式，比如：upload()。
 * 2.封装具体的实现细节。
 *   如跟阿里云相关的特殊上传（或下载）流程不应该暴露给调用者。
 *   我们对上传（或下载）流程进行封装，对外提供一个包裹所有上传（或下载）细节的方法，给调用者使用。
 * 3.为实现类定义抽象的接口。
 *   具体的实现类都依赖统一的接口定义，遵从一致的上传功能协议。使用者依赖接口，而不是具体的实现类来编程。
 *
 */
public class AliyunImageStore {

    //...省略属性、构造函数等...

    public void createBucketIfNotExist(String bucketName) {
        //...创建 bucket 代码逻辑
        //...失败会抛出异常...
    }

    public String generateAccessToken() {
        //...根据 accesskey/secrectkey 等生成 access token
        return "token";
    }

    public String uploadToAliyun(Image image, String bucketName, String accessToken) {
        //...上传图片到阿里云...
        //...返回图片存储在阿里云上的地址(url)...
        return "url";
    }

    public Image downloadFromAliyun(String url, String accessToken) {
        //...从阿里云下载图片...
        return null;
    }



}
