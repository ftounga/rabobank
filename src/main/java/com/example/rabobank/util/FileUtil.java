package com.example.rabobank.util;

import com.example.rabobank.enumeration.FileExtension;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static FileExtension getFileExtension(MultipartFile recordFile){
        String extension = recordFile.getOriginalFilename().split("\\.")[1];
        return FileExtension.fromExtension(extension);
    }
}
