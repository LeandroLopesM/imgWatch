package com.lean;

import com.lean.util.AspectRatio;
import com.lean.util.Util;
import sun.awt.image.PNGImageDecoder.PNGException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Scanner;

public class imgWatch {
    int index = 1;

    public imgWatch() {
        Scanner scn = new Scanner(System.in);

        String folderPath = scn.nextLine();
        scn.close();

        if(!(Util.folderExists(folderPath))) {
            System.err.println("'" + folderPath + "' Is not a folder");
            System.exit(1);
        }

        List<String> imgPaths = Util.folderContent(folderPath);

        if(imgPaths.isEmpty()) {
            System.out.println("No images!");
            System.exit(1);
        }

        System.out.println("\n" + imgPaths.size() + " images indexed.");

        JFrame mainWindow = new JFrame( "imgWatch" );
        ImageIcon topImg = new ImageIcon( imgPaths.get(index) );
        JLabel imgLabel = new JLabel(topImg);

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setBackground(new Color(30, 30, 30));

        imgLabel.setIcon(
            AspectRatio.resize(
                imgPaths.get(index), 800, 600
            )
        );

        mainWindow.getContentPane().add(imgLabel, BorderLayout.CENTER);

        mainWindow.getContentPane().setBackground(new Color(40, 40, 40));

        mainWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if(index == imgPaths.size()-1) index = 0;
                    else index++;

                    try {
                        paint(mainWindow, imgLabel, imgPaths);
                    } catch (PNGException pngCRC) {
                        System.err.println("Image corrupt, skipping");
                        index++;
                    }

                } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if(index == 0) index = imgPaths.size() + 1;
                    else index--;

                    try {
                        paint(mainWindow, imgLabel, imgPaths);
                    } catch (PNGException crc) {
                        System.err.println("Image corrupt, skipping");
                        index++;
                    }
                } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("ESC detected : exiting");
                    System.exit(0);
                }
            }

        });

        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public void paint(JFrame window, JLabel l, List<String> img) throws PNGException{
        l.setIcon(
            AspectRatio.resize(
                img.get(index), window.getHeight(), window.getWidth()
            )
        );

        window.revalidate();
        window.repaint();
    }
    public static void main(String[] args) {
        new imgWatch();
    }
}