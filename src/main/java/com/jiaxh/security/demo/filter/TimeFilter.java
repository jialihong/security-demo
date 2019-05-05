package com.jiaxh.security.demo.filter;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @Auther: jiaxh
 * @Date: 2019/4/29 16:03
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TimeFilter start ...");
        long start = new Date().getTime();
        chain.doFilter(request,response);
        System.out.println("TimeFilter 耗时：" + (new Date().getTime() - start) + "s");
        System.out.println("TimeFilter end ...");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter init ...");
    }

    @Override
    public void destroy() {

    }
}
