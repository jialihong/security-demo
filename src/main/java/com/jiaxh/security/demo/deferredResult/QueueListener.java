package com.jiaxh.security.demo.deferredResult;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * ContextRefreshedEvent Spring容器初始化完毕的事件
 * @Auther: jiaxh
 * @Date: 2019/5/5 10:46
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * 使用DeferredResult进行异步处理，代表应用1的线程2
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        new Thread(()->{
            while(true){
                if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                    final String completeOrder = mockQueue.getCompleteOrder();
                    logger.info("返回订单处理结果："+completeOrder);
                    deferredResultHolder.getMap().get(completeOrder).setResult("place order success");
                    mockQueue.setCompleteOrder(null);
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
