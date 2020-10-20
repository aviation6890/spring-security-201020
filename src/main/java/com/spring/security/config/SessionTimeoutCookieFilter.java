package com.spring.security.config;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SessionTimeoutCookieFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initialization SessionTimeoutCookieFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) servletRequest;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        long currentTime = System.currentTimeMillis();
        String expireTime = Long.toString(currentTime + httpRequest.getSession().getMaxInactiveInterval() * 1000);
        Cookie cookie = new Cookie("ServeTime", Long.toString(currentTime));

        httpResponse.addCookie(cookie);

        if (httpRequest.getRemoteUser() != null) {
            cookie = new Cookie("sessionExpire ", expireTime);
        }

        cookie.setPath("/");
        httpResponse.addCookie(cookie);

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
