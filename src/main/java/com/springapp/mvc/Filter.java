package com.springapp.mvc;


import org.springframework.web.multipart.MultipartFile;


public class Filter {
    private MultipartFile image;
    private boolean brightness, greyscale, threshold, blur, sharpen, emboss, edge, invert;
    private float brightLevel, thresholdLevel;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public boolean isBrightness() {
        return brightness;
    }

    public void setBrightness(boolean brightness) {
        this.brightness = brightness;
    }


    public boolean isGreyscale() {
        return greyscale;
    }

    public void setGreyscale(boolean greyscale) {
        this.greyscale = greyscale;
    }

    public boolean isThreshold() {
        return threshold;
    }

    public void setThreshold(boolean threshold) {
        this.threshold = threshold;
    }

    public boolean isBlur() {
        return blur;
    }

    public void setBlur(boolean blur) {
        this.blur = blur;
    }

    public boolean isSharpen() {
        return sharpen;
    }

    public void setSharpen(boolean sharpen) {
        this.sharpen = sharpen;
    }

    public boolean isEmboss() {
        return emboss;
    }

    public void setEmboss(boolean emboss) {
        this.emboss = emboss;
    }

    public boolean isEdge() {
        return edge;
    }

    public void setEdge(boolean edge) {
        this.edge = edge;
    }

    public boolean isInvert() {
        return invert;
    }

    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    public float getBrightLevel() {
        return brightLevel;
    }

    public void setBrightLevel(float brightLevel) {
        this.brightLevel = brightLevel;
    }


    public float getThresholdLevel() {
        return thresholdLevel;
    }

    public void setThresholdLevel(float thresholdLevel) {
        this.thresholdLevel = thresholdLevel;
    }
}