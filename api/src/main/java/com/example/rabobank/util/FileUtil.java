package com.example.rabobank.util;

import com.example.rabobank.enumeration.FileExtension;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static FileExtension getFileExtension(String originalName){
        String extension = originalName.split("\\.")[1];
        return FileExtension.fromExtension(extension);
    }
}
