package com.springapp.mvc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;


public class FiltersImplementation {


    private Filter filter;
    private File imageFile;
    private File outputFile;
    private BufferedImage bufferedImage;

    public File getOutputFile() {
        return outputFile;
    }

    public FiltersImplementation(Filter filter, File imageFile) {
        this.filter = filter;
        this.imageFile = imageFile;

        getImageFromFile();

        identifyType();
        saveImageFromFile();
    }


    public String getOutputFilePath() {
        return outputFile.getPath();
    }


    private void getImageFromFile() {

        try {
            File img = imageFile;
            bufferedImage = ImageIO.read(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImageFromFile() {

        try {
            outputFile = new File("/home/alicja/Desktop/projekty_src/tech_multi/FiltrObrazow/" + new Date().getTime() + ".jpg");
            ImageIO.write(bufferedImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void identifyType() {
        if (filter.isBrightness())
            bufferedImage = brightnessFilter();


        if (filter.isGreyscale())
            bufferedImage = greyscaleFilter();


        if (filter.isThreshold())
            bufferedImage = thresholdFilter();

        if (filter.isBlur())
            bufferedImage = blurFilter();

        if (filter.isSharpen())
            bufferedImage = sharpenFilter();

        if (filter.isEmboss())
            bufferedImage = embossFilter();

        if (filter.isEdge())
            bufferedImage = edgesFilter();

        if (filter.isInvert())
            bufferedImage = invertColorFilter();

    }


    private BufferedImage brightnessFilter() {
        float brightnessLevel = filter.getBrightLevel();

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                red *= brightnessLevel;
                if (red > 255) red = 255;

                green *= brightnessLevel;
                if (green > 255) green = 255;

                blue *= brightnessLevel;
                if (blue > 255) blue = 255;

                color = new Color(red, green, blue);

                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }

        return bufferedImage;

    }


    private BufferedImage greyscaleFilter() {
        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                red = green = blue = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                color = new Color(red, green, blue);
                int rgb = color.getRGB();
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;

    }

    private BufferedImage thresholdFilter() {

        float thresholdLevel = filter.getThresholdLevel();

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                if ((red + green + blue) / 3 >= thresholdLevel) {
                    color = Color.WHITE;
                } else {
                    color = Color.BLACK;
                }

                int rgb = color.getRGB();
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;

    }


    private BufferedImage blurFilter() {

        float[] matrix = new float[400];
        for (int i = 0; i < 400; i++)
            matrix[i] = 1.0f / 400.0f;

        BufferedImageOp blurFilter = new ConvolveOp(new Kernel(20, 20, matrix), ConvolveOp.EDGE_NO_OP, null);
        return blurFilter.filter(bufferedImage, null);


    }

    private BufferedImage sharpenFilter() {
        float[] sharpenMatrix = {0.0f, -1.0f, 0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f, 0.0f};
        BufferedImageOp sharpenFilter = new ConvolveOp(new Kernel(3, 3, sharpenMatrix),
                ConvolveOp.EDGE_NO_OP, null);
        return sharpenFilter.filter(bufferedImage, null);


    }

    private BufferedImage embossFilter() {
        float[] embossMatrix = {-2, -1, 0, -1, 1, 1, 0, 1, 2};
        BufferedImageOp embosFilter = new ConvolveOp(new Kernel(3, 3, embossMatrix),
                ConvolveOp.EDGE_NO_OP, null);
        return embosFilter.filter(bufferedImage, null);
    }

    private BufferedImage edgesFilter() {
        float[] edgeMatrix = {0, 1, 0, 1, -4, 1, 0, 1, 0};
        BufferedImageOp edgeFilter = new ConvolveOp(new Kernel(3, 3, edgeMatrix),
                ConvolveOp.EDGE_NO_OP, null);
        return edgeFilter.filter(bufferedImage, null);
    }

    private BufferedImage invertColorFilter() {
        byte[] invertArray = new byte[256];

        for (int counter = 0; counter < 256; counter++) {
            invertArray[counter] = (byte) (255 - counter);
        }

        BufferedImageOp invertFilter = new LookupOp(new ByteLookupTable(0, invertArray), null);
        return invertFilter.filter(bufferedImage, null);
    }


    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
