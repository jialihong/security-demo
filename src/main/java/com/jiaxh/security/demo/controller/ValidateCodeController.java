package com.jiaxh.security.demo.controller;

import com.jiaxh.security.demo.validate.code.ImageCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@RestController
public class ValidateCodeController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //1、根据随机数生成图片
        ImageCode imageCode = createImageCode(request);
        //2、将随机数存放到session中  request/key/value
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        //3、将生成的图片写到响应中
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());

    }

    private ImageCode createImageCode(HttpServletRequest request) {
        int width = 67;
        int height = 23;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        Random random = new Random();
        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0,0,width,height);
        graphics.setFont(new Font("Time New Roman",Font.ITALIC,20));
        graphics.setColor(getRandColor(160,200));
        for (int i = 0;i<155;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x,y,xl,yl);
        }

        String sRand = "";
        for (int i= 0; i < 4; i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            graphics.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            graphics.drawString(rand,13 * i + 6,16);
        }
        graphics.dispose();
        return new ImageCode(bufferedImage,sRand,60);
    }

    /**
     * 给定范围获得随机颜色
     * @param i
     * @param j
     * @return
     */
    private Color getRandColor(int i, int j) {
        Random random = new Random();
        if(i>255) i=255;
         if(j>255) j=255;
        int r=i+random.nextInt(j-i);
        int g=i+random.nextInt(j-i);
        int b=i+random.nextInt(j-i);
        return new Color(r,g,b);

    }


}
