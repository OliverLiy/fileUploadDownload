package com.javayz.fileuploadanddownload.controller;

import com.javayz.fileuploadanddownload.entity.Files;
import com.javayz.fileuploadanddownload.response.Result;
import com.javayz.fileuploadanddownload.response.ResponseCode;
import com.javayz.fileuploadanddownload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author by: Liyu
 * @ClassName: FileController
 * @Description: TODO 文件上传
 * @Date: 2021/1/12 17:07
 */

@RestController
@RequestMapping("/api")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upLoadFiles(MultipartFile multipartFile){
        if (multipartFile.isEmpty()){
            return new Result(ResponseCode.FILE_EMPTY.getCode(),ResponseCode.FILE_EMPTY.getMsg(),null);
        }
        return fileService.upLoadFiles(multipartFile);
    }

    @RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
    public void downloadFiles(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        OutputStream outputStream=null;
        InputStream inputStream=null;
        Files files = fileService.getFileById(id);
        String fileName = files.getFileName();
        inputStream=fileService.getFileInputStream(files);
        response.setHeader("Content-Disposition", "attachment;filename=" +  new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        // 获取输出流
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream!=null){
                    inputStream.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
