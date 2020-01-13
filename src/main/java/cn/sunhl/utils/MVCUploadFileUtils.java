package cn.sunhl.utils;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @Author: sunhailong
 * @Date: 2020/1/13 下午2:33
 * @Desc:
 */
public class MVCUploadFileUtils {

    private static final Logger logger = Logger.getLogger(MVCUploadFileUtils.class);

    public static List<FileObject> saveUploadFile(HttpServletRequest request, String folderName, String allowFileSuffix) throws Exception {
        ArrayList list = new ArrayList();

        try {
            request.setCharacterEncoding("UTF-8");
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                multiRequest.setCharacterEncoding("UTF-8");
                Iterator<String> iter = multiRequest.getFileNames();
                int year = DateTimeUtils.getNowYear();
                int month = DateTimeUtils.getNowMonth();
                int day = DateTimeUtils.getNowDay();
                String pathFolder = "/" + year + "/" + month + "/" + day + "/";
                boolean result = FileUtils.forceMkdir(folderName + pathFolder));
                if (result) {
                    logger.debug("Create file folder[" + folderName + pathFolder + "] success.");
                }

                while(iter.hasNext()) {
                    MultipartFile multipartFile = multiRequest.getFile((String)iter.next());
                    if (multipartFile != null) {
                        String myFileName = multipartFile.getOriginalFilename();
                        if (!myFileName.trim().isEmpty()) {
                            String fileExt = StringUtils.getFileExt(myFileName);
                            if (null != allowFileSuffix && allowFileSuffix.length() > 0 && !allowFileSuffix.toLowerCase().contains("." + fileExt.toLowerCase())) {
                                throw new Exception("上传文件格式错误.只能上传后缀名为：" + allowFileSuffix + " 的文件。");
                            }

                            String guid = UUID.randomUUID().toString().replace("-", "");
                            String path = pathFolder + "/" + guid + "_" + myFileName;
                            String fileFullName = folderName + path;
                            File file = new File(fileFullName);
                            if (file.exists()) {
                                try {
                                    file.renameTo(new File(fileFullName));
                                } catch (Exception var20) {
                                    logger.error(var20);
                                    throw var20;
                                }

                                multipartFile.transferTo(new File(fileFullName));
                            } else {
                                multipartFile.transferTo(new File(fileFullName));
                            }

                            logger.debug("Upload file to [" + fileFullName + "] success.");
                            FileObject fileObject = new FileObject();
                            fileObject.setFilePath(path);
                            fileObject.setFileName(myFileName);
                            fileObject.setFileSuffix(fileExt);
                            fileObject.setFileSize(multipartFile.getSize());
                            fileObject.setFileGuid(guid);
                            list.add(fileObject);
                        }
                    }
                }
            }

            return list;
        } catch (Exception var21) {
            logger.error("Upload file error.", var21);
            throw var21;
        }
    }

    public static List<FileObject> saveUploadFile(HttpServletRequest request, String folderName) throws Exception {
        return saveUploadFile(request, folderName, "");
    }
}
