package net.thecodersbreakfast.spring.model;

public class FileInfo {

    private String path;
    private String name;
    private String parent;
    private boolean directory;
    private long lastModified;
    private String contentType;

    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
    public String getParent() {
        return parent;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }
    public boolean isDirectory() {
        return directory;
    }

    public long getLastModified() {
        return lastModified;
    }
    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getContentType() {
        return contentType;
    }

}
