package com.jiaxh.security.demo.configuration;

import com.jiaxh.security.demo.filter.TimeFilter;
import com.jiaxh.security.demo.intercepter.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FilterRegistrationBean 将第三方filter添加到springboot容器中
 * @Auther: jiaxh
 * @Date: 2019/4/29 16:17
 */
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 适用于同步单线程的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    /**
     * 适用于异步多线程的拦截器
     * @param
     */
//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        //针对使用runnable进行异步处理的拦截器
//        configurer.registerCallableInterceptors();
//        //针对使用DeferredResult进行异步处理的拦截器
//        configurer.registerDeferredResultInterceptors();
//        //给子线程设置过期时间
//        configurer.setDefaultTimeout(5000);
//        //设置自定义的线程池（使用runnable进行异步处理时，spring会使用默认的线程）
//        configurer.setTaskExecutor();
//    }

    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        //设置过滤器
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        //设置要过滤的urls
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);

        return filterRegistrationBean;
    }
}
