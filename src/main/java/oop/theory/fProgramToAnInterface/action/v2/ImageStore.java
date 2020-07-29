package oop.theory.fProgramToAnInterface.action.v2;

import java.awt.*;

public interface ImageStore {
    String upload(Image image, String bucketName);
    Image download(String url);
}
