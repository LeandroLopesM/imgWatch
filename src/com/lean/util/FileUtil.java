package com.lean.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static boolean folderExists(String filePath) {
		Path path = Paths.get(filePath);
		
		if(!Files.exists(path) || !Files.isDirectory(path)) {
			return false;
		}
		return true;
	}
	
	public static List<String> folderContent(String folderPath) {
		List<String> content = new ArrayList<>();
//		File directoryFolder = new File(folderPath);
		File[] directoryContent = new File(folderPath).listFiles();
		
		for(int i = 0; i < directoryContent.length; i++) {
			if(directoryContent[i].isDirectory() || !(MatchWildcard.isMatch(directoryContent[i].getName(), "*.jpg")
					|| MatchWildcard.isMatch(directoryContent[i].getName(), "*.png"))) {
				System.err.println("[" + directoryContent[i].getName() + "] Not imageFile, skipping...");
				continue;
			} else {
				System.out.println("[" + directoryContent[i].getName() + "] Is an imageFile, indexing...");
				content.add(directoryContent[i].getPath());
			}
		}
		
		return content;
	}
}
