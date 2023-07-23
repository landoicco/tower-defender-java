package helpers;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class ImageFix {

    // Rotate single image
    public static BufferedImage GetRotatedImage(BufferedImage img, int rotAngle) {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImg = new BufferedImage(width, height, img.getType());
        Graphics2D g2d = newImg.createGraphics();

        g2d.rotate(Math.toRadians(rotAngle), (width / 2), (height / 2));
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return newImg;
    }

    // Create an image from two images
    public static BufferedImage BuildImage(BufferedImage[] imgs) {
        int width = imgs[0].getWidth();
        int height = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(width, height, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (BufferedImage image : imgs) {
            g2d.drawImage(image, 0, 0, null);
        }
        g2d.dispose();

        return newImg;
    }

    // Rotate an image created from two images, second image only is rotated
    public static BufferedImage GetRotatedBuildedImage(BufferedImage[] imgs, int rotAngle, int rotAtIndex) {
        int width = imgs[0].getWidth();
        int height = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(width, height, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();

        for (int i = 0; i < imgs.length; i++) {
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(rotAngle), (width / 2), (height / 2));
            }
            g2d.drawImage(imgs[i], 0, 0, null);
            if (rotAtIndex == i) {
                g2d.rotate(Math.toRadians(-rotAngle), (width / 2), (height / 2));
            }
        }

        g2d.dispose();
        return newImg;
    }

    // Rotate an image created from two images, second image only is rotated +
    // animation
    public static BufferedImage[] GetRotatedBuildedImage(BufferedImage[] imgs, BufferedImage topImg, int rotAngle,
            int rotAtIndex) {
        int width = imgs[0].getWidth();
        int height = imgs[0].getHeight();
        BufferedImage[] arr = new BufferedImage[imgs.length];

        for (int i = 0; i < arr.length; i++) {
            BufferedImage newImg = new BufferedImage(width, height, imgs[0].getType());
            Graphics2D g2d = newImg.createGraphics();

            g2d.drawImage(imgs[i], 0, 0, null);
            g2d.rotate(Math.toRadians(rotAngle), (width / 2), (height / 2));
            g2d.drawImage(topImg, 0, 0, null);

            g2d.dispose();

            arr[i] = newImg;
        }

        return arr;
    }
}
