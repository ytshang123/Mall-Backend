package project.linkortech.test.utils;

import java.io.IOException;
import java.io.InputStream;

public class RuntimeCmdUtil {

    public static String exec(String cmd) throws InterruptedException, IOException {
        String[] cmds = {"/bin/sh","-c",cmd};
        Process pro = Runtime.getRuntime().exec(cmds);
        pro.waitFor();
        InputStream in = pro.getInputStream();
        byte[] resBytes = new byte[in.available()];
        in.read(resBytes);
        return new String(resBytes);
    }

}
