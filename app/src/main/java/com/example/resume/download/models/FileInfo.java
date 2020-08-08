package com.example.resume.download.models;

import androidx.annotation.NonNull;

public class FileInfo {
    int id;
    int fileSize;
    String fileName;
    String path;

    public FileInfo(int id, int fileSize, String fileName, String path) {
        this.id = id;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.path = path;
    }

    public FileInfo(int fileSize, String fileName, String path) {
        this(0, fileSize, fileName, path);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @NonNull
    @Override
    public String toString() {

        String result = "ID : "+ getId()+ "\n"+ "FileName : "+ fileName.replaceAll("%20"," ").replaceAll("%7"," ") + "\n" + "Path : " + path.replaceAll("%20"," ") + "\n" + "Size :" + fileSize/1000 +"KB";
        return result;
    }
}
