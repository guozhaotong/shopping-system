package com.guozhaotong.shoppingsystem.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 郭朝彤
 * @date 2019/3/4.
 */
public class FileUpload {


    public static void main(String[] args){
        File file = new File("M:\\");
        System.out.println(Arrays.toString(file.list()));
    }
}
