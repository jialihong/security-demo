package com.jiaxh.security.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception{

        String userList = mockMvc
                .perform(MockMvcRequestBuilders.get("/user")
                        .param("username", "jia")
                        .param("age", "16")
                        .param("ageTo", "20")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value("3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(userList);
    }

    @Test
    public void getUser() throws Exception{
        String user = mockMvc.perform(MockMvcRequestBuilders.get("/user/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(user);
    }

    @Test
    public void getUserFailed() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void createUser() throws Exception{
        String contentJson = "{\"username\":\"tom\",\"age\":\"16\",\"password\":null}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(contentJson))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateUser() throws Exception{
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//        Date date = new Date(LocalDateTime.now().plusYears(1L).atZone(ZoneId.systemDefault()).toEpochSecond());
        System.out.println(date.getTime());
        String contentJson = "{\"id\":\"1\",\"username\":\"tom\",\"age\":\"16\",\"password\":\"123456\",\"birthday\":"+ date.getTime() +"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(contentJson))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fileUpload() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
        .file(new MockMultipartFile("file","test.txt","multipart/form-data","FILE UPLOAD".getBytes())))
        .andExpect(MockMvcResultMatchers.status().isOk());
//        .andReturn().getResponse().getContentAsString();
    }

}
