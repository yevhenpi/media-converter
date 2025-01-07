package ua.pidopryhora.model;


import java.io.Serializable;

public class MediaMessage implements Serializable {

    private String fileName;
    private byte[] fileBytes;
    private String targetFormat;

    public MediaMessage(){}

    public MediaMessage(String fileName, byte[] fileBytes, String targetFormat) {
        this.fileName = fileName;
        this.fileBytes = fileBytes;
        this.targetFormat = targetFormat;
    }

    // Getters and setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public String getTargetFormat() {
        return targetFormat;
    }

    public void setTargetFormat(String targetFormat) {
        this.targetFormat = targetFormat;
    }
}
