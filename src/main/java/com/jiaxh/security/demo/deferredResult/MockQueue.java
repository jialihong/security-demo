package com.jiaxh.security.demo.deferredResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 模拟消息队列 使用DeferredResult进行异步处理，代表应用2
 * @Auther: jiaxh
 * @Date: 2019/5/5 10:25
 */
@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 下单的订单号
     */
    private String placeOrder;
    /**
     * 处理完成的订单号
     */
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception{
        new Thread(()->{
            logger.info("接到下单请求，" + placeOrder);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            logger.info("下单请求处理完毕，" + placeOrder);
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
