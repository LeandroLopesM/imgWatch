package com.lean.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static boolean folderExists(String filePath) {
        Path path = Paths.get(filePath);

        return Files.exists(path) && Files.isDirectory(path);
    }

    public static List<String> folderContent(String folderPath) {
        List<String> content = new ArrayList<>();
        File[] directoryContent = new File(folderPath).listFiles();

        if(!Files.isDirectory(new File(folderPath).toPath()) || directoryContent == null) {
            System.err.println("ERROR -> FileUtil:folderContent() -> Path provided is not directory or is empty");
            System.exit(1);
        }

        for (File dir : directoryContent) {
            if (dir.isDirectory() || !dir.getName().matches("^(.+).(jpg|jpeg|png|gif)$")) {
                System.err.println("[" + dir.getName() + "] Not imageFile, skipping...");
            } else {
                System.out.println("[" + dir.getName() + "] Is an imageFile, indexing...");
                content.add(dir.getPath());
            }
        }

        if(content.isEmpty()) {
            System.err.println("No images found in folder '" + folderPath + "'");
            System.exit(0);
        }

        return content;
    }
}