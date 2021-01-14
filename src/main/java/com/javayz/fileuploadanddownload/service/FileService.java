package com.javayz.fileuploadanddownload.service;

import com.javayz.fileuploadanddownload.entity.Files;
import com.javayz.fileuploadanddownload.response.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author by: Liyu
 * @ClassName: FileService
 * @Description: TODO
 * @Date: 2021/1/12 17:16
 */

public interface FileService {

    /**
     * 文件上传接口
     * @param file
     * @return
     */
    Result upLoadFiles(MultipartFile file);

    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    Files getFileById(String id);

    /**
     * 根据id获取数据流
     * @param files
     * @return
     */
    InputStream getFileInputStream(Files files);
}
