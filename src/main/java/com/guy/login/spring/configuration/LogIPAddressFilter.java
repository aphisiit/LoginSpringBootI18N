package com.guy.login.spring.configuration;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LogIPAddressFilter extends OncePerRequestFilter {

    private static final String KEY = "X-IP-ADDRESS";
    private static Logger LOGGER = LoggerFactory.getLogger(LogIPAddressFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        ThreadContext.put(KEY, request.getRemoteAddr());
        LOGGER.info("{} : {}",KEY,request.getRemoteAddr());
        chain.doFilter(request, response);
        ThreadContext.clearAll();

    }
}
