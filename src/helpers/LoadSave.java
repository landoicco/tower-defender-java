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

import objects.PathPoint;

public class LoadSave {

    public static BufferedImage GetSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res/spriteatlas_roads.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public static BufferedImage GetSpriteAtlas(String atlasName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res/" + atlasName + ".png");
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

    public static void SaveLevel(String name, int[][] idArray, PathPoint start, PathPoint end) {
        File levelFile = new File("res/" + name + ".txt");

        if (levelFile.exists()) {
            int[] oneDimArray = Utils.TwoDTo1DintArray(idArray);
            WriteToFile(levelFile, oneDimArray, start, end);
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

        WriteToFile(newLevel, idArr, new PathPoint(0, 0), new PathPoint(0, 0));
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

    public static ArrayList<PathPoint> GetLevelPathPoints(String name) {
        File lvlFile = new File("res/" + name + ".txt");
        if (!lvlFile.exists()) {
            System.out.println("File: " + name + " does not exists");
            return null;
        }

        ArrayList<Integer> list = ReadFromFile(lvlFile);
        ArrayList<PathPoint> points = new ArrayList<>();
        points.add(new PathPoint(list.get(400), list.get(401)));
        points.add(new PathPoint(list.get(402), list.get(403)));

        return points;

    }

    private static void WriteToFile(File file, int[] idArr, PathPoint start, PathPoint end) {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (int i : idArr) {
                pw.println(i);
            }
            pw.println(start.getxCord());
            pw.println(start.getyCord());
            pw.println(end.getxCord());
            pw.println(end.getyCord());
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
