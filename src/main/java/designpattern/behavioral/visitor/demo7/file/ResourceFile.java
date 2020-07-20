package designpattern.behavioral.visitor.demo7.file;

public abstract class ResourceFile {

    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract ResourceFileType getType();

}
