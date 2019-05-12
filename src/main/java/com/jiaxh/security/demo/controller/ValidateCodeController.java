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

@RestController
public class ValidateCodeController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

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
        return null;
    }


}
