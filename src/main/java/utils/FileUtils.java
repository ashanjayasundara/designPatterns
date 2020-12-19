package utils;

import java.io.File;

/**
 * @author ashan on 2020-12-19
 */
public class FileUtils {
    public static void createFile(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

    }
}
