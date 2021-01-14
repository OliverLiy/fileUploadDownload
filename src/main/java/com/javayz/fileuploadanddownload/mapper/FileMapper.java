package com.javayz.fileuploadanddownload.mapper;

import com.javayz.fileuploadanddownload.entity.Files;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author by: Liyu
 * @ClassName: fileMapper
 * @Description: TODO
 * @Date: 2021/1/13 9:42
 */

@Mapper
@Repository
public interface FileMapper {
    /**
     * 将数据信息插入到数据库
     * @param files
     */
    void insertFile(Files files);

    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    Files selectFileById(String id);
}
