package com.fastcampus.controller.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = "*.jsp",
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharacterEncodingFilter implements Filter {

    private String boardEncoding;

    public CharacterEncodingFilter() {
        System.out.println("===> CharacterEncodingFilter 생성");
   }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        boardEncoding = fConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 모든 서블릿이 실행되기 전에 인코딩을 일괄적으로 처리한다.
        request.setCharacterEncoding(boardEncoding);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
