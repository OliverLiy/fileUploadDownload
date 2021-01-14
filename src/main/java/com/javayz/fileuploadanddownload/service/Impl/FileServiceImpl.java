package com.javayz.fileuploadanddownload.service.Impl;

import com.javayz.fileuploadanddownload.entity.Files;
import com.javayz.fileuploadanddownload.response.Result;
import com.javayz.fileuploadanddownload.mapper.FileMapper;
import com.javayz.fileuploadanddownload.response.ResponseCode;
import com.javayz.fileuploadanddownload.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author by: Liyu
 * @ClassName: FileServiceImpl
 * @Description: TODO
 * @Date: 2021/1/12 17:34
 */
@Service
public class FileServiceImpl implements FileService {


    @Value("${file.save-path}")
    private String savePath;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Result upLoadFiles(MultipartFile file) {
        long MAX_SIZE=2097152L;
        String fileName=file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)){
            return new Result(ResponseCode.FILE_NAME_EMPTY.getCode(),ResponseCode.FILE_NAME_EMPTY.getMsg(),null);
        }
        if (file.getSize()>MAX_SIZE){
            return new Result(ResponseCode.FILE_MAX_SIZE.getCode(),ResponseCode.FILE_MAX_SIZE.getMsg(),null);
        }
        String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
        String newName = System.currentTimeMillis() + suffixName;
        File newFile=new File(savePath,newName);
        if (!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();
        }
        try {
            //文件写入
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files files=new Files(newFile.getPath(),fileName,suffixName);
        fileMapper.insertFile(files);
        return new Result(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),"数据上传成功");
    }

    @Override
    public Files getFileById(String id) {
        Files files = fileMapper.selectFileById(id);
        return files;
    }

    @Override
    public InputStream getFileInputStream(Files files) {
        File file=new File(files.getFilePath());
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
