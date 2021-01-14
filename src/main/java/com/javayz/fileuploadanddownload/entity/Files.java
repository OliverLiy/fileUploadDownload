package com.javayz.fileuploadanddownload.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author by: Liyu
 * @ClassName: Files
 * @Description: TODO
 * @Date: 2021/1/12 16:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Files implements Serializable {

    private static final long serialVersionUID=1L;
    /**
     * 文件存储路径
     */
    private String filePath;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件后缀名
     */
    private String fileSuffix;

}
