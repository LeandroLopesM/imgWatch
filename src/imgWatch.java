import com.lean.util.FileUtil;
import com.lean.util.AspectRatio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class imgWatch {	
	
	int index = 1;
	
	
	
	public imgWatch() {
		Scanner scn = new Scanner(System.in);
		
		String folderPath = scn.nextLine();
		scn.close();
		
		if(!(FileUtil.folderExists(folderPath))) {
			System.err.println("' " + folderPath + " ' Is not a folder");
			System.exit(1);
		}
		
		List<String> imgPaths = FileUtil.folderContent(folderPath);

		if(imgPaths.size() == 1) {
			System.out.println("No images!");
			System.exit(1);
		}
		
		System.out.println("\n" + imgPaths.size() + " images indexed.");
		
		JFrame mainWindow = new JFrame("imgWatch");
		ImageIcon topImg = new ImageIcon(imgPaths.get(index));
		JLabel imgLabel = new JLabel(topImg);
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		mainWindow.setBackground(new Color(30, 30, 30));
		
		imgLabel.setIcon(AspectRatio.resizeToAspectRatio(
				imgPaths.get(index), 800,
				600));
		
		mainWindow.getContentPane().add(imgLabel, BorderLayout.CENTER);
		
		Color cl = new Color(50, 50, 50);		
		mainWindow.getContentPane().setBackground(cl);
		mainWindow.setLocation(500, 300);;
		
		mainWindow.addKeyListener(new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					if(index == imgPaths.size()-1) index = 0;
					else index++;
					
					imgLabel.setIcon(AspectRatio.resizeToAspectRatio(
							imgPaths.get(index), mainWindow.getHeight(),
							mainWindow.getWidth()));
					
					mainWindow.revalidate();
					mainWindow.repaint();
					
				} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.out.println("ESC detected : exiting");
					System.exit(0);
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {		}
			@Override
			public void keyPressed(KeyEvent e) {	}

		});
		
		mainWindow.pack();
		mainWindow.setAlwaysOnTop(true);
		mainWindow.setVisible(true);
		
	}
	public static void main(String[] args) {
		new imgWatch();
	}
}