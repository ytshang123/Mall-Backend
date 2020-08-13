package project.linkortech.test.mod_download_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.lang.System.out;

/**
 * Created by asus on 2018/11/22.
 */
public class ZipService {
    private static final int  BUFFER_SIZE = 2 * 1024;
    public static void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException{
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            System.out.print("Zip success");
        }catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
