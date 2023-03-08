package com.tuanla.springserver.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

public class FileUtil {
    public static void writeToTempFile(String filePath) throws Exception {
        File file = new File(CurrentWorkingDirectory.printCurrentWorkingDirectory4() + "/src/main/java/vn/com/telsoft/tmp/targetFile.xlsx");
        InputStream initialStream = new FileInputStream(file);
        java.nio.file.Files.copy(
                initialStream,
                file.toPath(),
                StandardCopyOption.REPLACE_EXISTING);

        IOUtils.closeQuietly(initialStream);
    }
}
