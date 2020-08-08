package com.example.resume.download.models;

public class Music {

    String name;
    String path;
    int imageResource;

    public Music(String name, String path, int imageResource) {
        this.name = name;
        this.path = path;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
