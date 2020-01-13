package cn.sunhl.utils;

import java.io.Serializable;

/**
 * @Author: sunhailong
 * @Date: 2020/1/13 下午2:38
 * @Desc: 文件对象
 */
public class FileObject implements Serializable {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 附件后缀名
     */
    private String fileSuffix;

    /**
     * 附件唯一字符串
     */
    private String fileGuid;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileGuid() {
        return fileGuid;
    }

    public void setFileGuid(String fileGuid) {
        this.fileGuid = fileGuid;
    }

}
