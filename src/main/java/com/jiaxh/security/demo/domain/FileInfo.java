package com.jiaxh.security.demo.domain;

/**
 * @Auther: jiaxh
 * @Date: 2019/4/29 22:40
 */
public class FileInfo {

    public FileInfo(String path) {
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
