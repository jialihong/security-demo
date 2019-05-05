package com.jiaxh.security.demo.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/5 15:38
 */
public class MockServer {
    public static void main(String[] args) throws Exception{
        WireMock.configureFor(8030);
        //清空之前的所有配置
        WireMock.removeAllMappings();
        //mock一个接口
        sendMock("/order/1", "1");

        //mock另外一个接口
        sendMock("/order/2", "2");
    }

    private static void sendMock(String url, String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/json/"+ fileName +".txt");
        final String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\r\n");
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
