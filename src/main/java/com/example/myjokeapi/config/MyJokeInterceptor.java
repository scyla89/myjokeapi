package com.example.myjokeapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyJokeInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(MyJokeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("PreHandle method is calling.");

/*        String requestURI = request.getRequestURI();
        log.info("Request URI = {}", requestURI);
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("Query parameters: ");
        parameterMap.forEach( (name, value) -> {
            log.info(name + " = " + Arrays.toString(value));
        });*/

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("PostHandle method is calling.");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        log.info("Request and Response is completed.");
    }
}
