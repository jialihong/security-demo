package com.jiaxh.security.demo.properties;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/17 10:14
 * 默认配置
 */
public class ImageCodeProperties {
    private int width = 67;
    private int heigth = 23;
    private int length = 4;
    private int expireIn = 60;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
