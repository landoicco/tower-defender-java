package helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res/spriteatlas.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public static void CreateFile() {
        File file = new File("res/test.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CreateLevel(String name, int[] idArr) {
        File newLevel = new File("res/" + name + ".txt");

        if (newLevel.exists()) {
            System.out.println("File: " + name + " already exists!");
            return;
        }

        // If file do not exist, create one
        try {
            newLevel.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WriteToFile(newLevel, idArr);
    }

    private static void WriteToFile(File file, int[] idArr) {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (int i : idArr) {
                pw.println(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void ReadFromFile() {
        File txtFile = new File("res/test.txt");
        try (Scanner sc = new Scanner(txtFile)) {
            System.out.println(sc.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
