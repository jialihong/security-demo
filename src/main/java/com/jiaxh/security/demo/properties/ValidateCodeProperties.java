package com.jiaxh.security.demo.properties;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/17 10:19
 * 封装图形验证码和短信验证码
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
