package com.javayz.fileuploadanddownload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by: Liyu
 * @ClassName: Result
 * @Description: TODO
 * @Date: 2021/1/12 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String message;
    private Object data;
}
