package com.abc.io;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utility {

    /**
     * Just A converter method for MultiPart file to io.File conversion
     * @param mFile
     * @return
     * @throws IOException
     */
    public static File convert(MultipartFile mFile) throws IOException {
        File file = new File(mFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(mFile.getBytes());
        fos.close();
        return file;
    }
}
