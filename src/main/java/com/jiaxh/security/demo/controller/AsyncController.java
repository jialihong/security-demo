package com.jiaxh.security.demo.controller;

import com.jiaxh.security.demo.deferredResult.DeferredResultHolder;
import com.jiaxh.security.demo.deferredResult.MockQueue;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @Auther: jiaxh
 * @Date: 2019/5/5 09:28
 */
@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * 使用Runnable进行异步处理
     * @return
     * @throws Exception
     */
    @GetMapping("/getOrder")
    @ApiOperation(value = "使用Runnable进行异步处理")
    public Callable<String> getOrder(){
        logger.info("主线程开始......");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("子线程开始");
                Thread.sleep(2000);
                logger.info("子线程结束");
                return "success";
            }
        };
        logger.info("主线程结束......");
        return result;
    }

    /**
     * 使用DeferredResult进行异步处理，模拟应用1的线程1
     * @return
     * @throws Exception
     */
    @GetMapping("/order")
    public DeferredResult<String> order() throws Exception{
        logger.info("主线程开始......");
        final String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber,result);
        logger.info("主线程结束......");
        return result;
    }
}
