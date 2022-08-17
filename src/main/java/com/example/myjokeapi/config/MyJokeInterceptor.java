package com.example.myjokeapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyJokeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("PreHandle method is calling.");
        //TODO: authentication validation -> the whole process
        if (request.getHeader("Authorization") == null) {
            log.info("Authorization not sent.");
            log.info("Validation NOK.");
            //Change it back to FALSE once authorization is implemented
            return true;
        }
        else if (request.getHeader("Authorization").equals("Test")) {
            log.info("Validation OK.");
            return true;
        } else {
            log.info("Validation NOK.");
            return false;
        }
/*      log.info("Request intercepted: " + request.getHeader("Authorization"));
        String requestURI = request.getRequestURI();
        log.info("Request URI = {}", requestURI);
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("Query parameters: ");
        parameterMap.forEach( (name, value) -> {
            log.info(name + " = " + Arrays.toString(value));
        });*/
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
