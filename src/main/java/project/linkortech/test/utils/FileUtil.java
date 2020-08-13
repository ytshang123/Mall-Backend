package project.linkortech.test.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    /**
     * path + / + filename
     * return filePath
     */
    public static String createFile(String path, String filename, byte[] data) throws IOException {
        File filepath = new File(path);
        if(!filepath.exists()) {
            filepath.mkdirs();
        }
        File file = new File(filepath + "/" + filename);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bos.write(data);
        bos.flush();
        bos.close();
        return filepath + "/" + filename;
    }

}
