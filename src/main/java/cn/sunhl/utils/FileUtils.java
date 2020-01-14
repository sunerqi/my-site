package cn.sunhl.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Author: sunhailong
 * @Date: 2020/1/13 下午3:32
 * @Desc: 文件操作类
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class.getName());

    /**
     * 创建目录
     *
     * @param path 目录路径
     * @return
     * @throws Exception
     */
    public static boolean newFolders(String path) throws Exception {
        try {
            File myFilePath = new File(path);
            if (!myFilePath.exists()) {
                return myFilePath.mkdirs();
            }
            return true;
        } catch (Exception e) {
            logger.error("创建目录[" + path + "]失败。", e);
            throw e;
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    public static boolean fileExist(String filePath) throws Exception {
        File myFilePath = new File(filePath);
        return myFilePath.exists();
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static boolean deleteFile(String filePath) throws Exception {
        File myFilePath = new File(filePath);
        if (myFilePath.exists()) {
            return myFilePath.delete();
        }
        return false;
    }

    /**
     * 新建文件
     *
     * @param filePath    文件名称
     * @param fileContent 文件内容
     * @return
     * @throws Exception
     */
    public static boolean newFile(String filePath, String fileContent) throws Exception {
        File myFilePath = new File(filePath);
        if (myFilePath.exists()) {
            deleteFile(filePath);
        }
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(fileContent);
            writer.flush();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException ex) {
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                }
            }
        }
        return false;
    }
}
