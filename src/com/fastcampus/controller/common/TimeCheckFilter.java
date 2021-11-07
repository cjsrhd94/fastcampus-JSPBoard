package com.fastcampus.controller.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "*.do")
public class TimeCheckFilter implements Filter {

    public TimeCheckFilter() {
        System.out.println("===> TimeCheckFilter 생성");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---> init() 호출");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // HttpServletRequest는 ServletRequest를 상속받는다.
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));

        long startTime = System.currentTimeMillis();

        // 이 시점에 브라우저가 요청한 서블릿이 실행된다.
        chain.doFilter(request, response);

        long endTime = System.currentTimeMillis();
        System.out.println(path + " 요청 처리에 수행에 소요된 시간: " + (endTime - startTime) + "(ms)초");
    }

    @Override
    public void destroy() {
        System.out.println("---> destroy() 호출");
    }
}
