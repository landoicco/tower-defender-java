package helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    public static void SaveLevel(String name, int[][] idArray) {
        File levelFile = new File("res/" + name + ".txt");

        if (levelFile.exists()) {
            int[] oneDimArray = Utils.TwoDTo1DintArray(idArray);
            WriteToFile(levelFile, oneDimArray);
        } else {
            System.out.println("File: " + name + ".txt does not exist!");
            return;
        }
    }

    public static void CreateLevel(String name, int[] idArr) {
        File newLevel = new File("res/" + name + ".txt");

        if (newLevel.exists()) {
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

    public static int[][] GetLevelData(String name) {
        File lvlFile = new File("res/" + name + ".txt");
        if (!lvlFile.exists()) {
            System.out.println("File: " + name + " does not exists");
            return null;
        }

        ArrayList<Integer> list = ReadFromFile(lvlFile);

        return Utils.ArrayListTo2Dint(list, 20, 20);
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

    private static ArrayList<Integer> ReadFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
