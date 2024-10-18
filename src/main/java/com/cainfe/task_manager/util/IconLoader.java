package main.java.com.cainfe.task_manager.util;

import java.net.URL;

import javax.swing.ImageIcon;

public class IconLoader {
    public static ImageIcon loadIcon(String path) {
        URL imgURL = IconLoader.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}