package com.lean.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class AspectRatio {
	public static ImageIcon resizeToAspectRatio(String imgPath, int maxHeight, int maxWidth) {
		Image img = new ImageIcon(imgPath).getImage();
		 // Calculate aspect ratio
       double widthRatio = (double) maxWidth / img.getWidth(null);
       double heightRatio = (double) maxHeight / img.getHeight(null);
       double ratio = Math.min(widthRatio, heightRatio);
       int scaledWidth = (int) (img.getWidth(null) * ratio);
       int scaledHeight = (int) (img.getHeight(null) * ratio);
		
       img = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
       
       return new ImageIcon(img);
	}
}
