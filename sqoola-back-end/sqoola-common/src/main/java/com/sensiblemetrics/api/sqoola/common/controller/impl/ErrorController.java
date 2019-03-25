//package com.sensiblemetrics.api.sqoola.common.controller.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.reactive.error.ErrorAttributes;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
//@RestController
//public class ErrorControllerA implements ErrorController {
//    private static final String PATH = "/error";
//
//    @Value("${include.error.stack.trace}")
//    private boolean includeStackTrace;
//
//    @Autowired
//    private ErrorAttributes errorAttributes;
//
//    @Override
//    public String getErrorPath() {
//        return PATH;
//    }
//
//    @RequestMapping(PATH)
//    public Error error(HttpServletRequest request, HttpServletResponse response) {
//        // Appropriate HTTP response code (e.g. 404) is set automatically
//        return new Error(response.getStatus(), getErrorAttributes(request, includeStackTrace));
//    }
//
//    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
//        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
//        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
//    }
//}
