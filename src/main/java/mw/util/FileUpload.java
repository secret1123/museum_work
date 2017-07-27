package mw.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by AnLu on
 * 2017/7/27 15:01.
 * ssm
 */
public class FileUpload {
    public static String upload(String filePath,MultipartFile file) {
        String photoFileName = getPhotoFileName();
        String originalFileName = file.getOriginalFilename();
        String extName = FilenameUtils.getExtension(originalFileName);
        try {
            String fileName = photoFileName.concat("."+extName);
            file.transferTo(new File(filePath,fileName));
            return fileName;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getPhotoFileName() {
        return Long.toString(System.nanoTime())+Double.toString(Math.random());
    }
}
