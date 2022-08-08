//package com.simba.missonGame.config;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CorsFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
////        response.setHeader("Content-type", "application/json");
//        response.setHeader("Access-Control-Allow-Origin", "*");
////        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods","*");
//        response.setHeader("Access-Control-Max-Age", "3600");
////        response.setHeader("Access-Control-Allow-Headers",
////                "Origin, X-Requested-With, Content-Type, Accept, Authorization");
//        response.setHeader("Access-Control-Allow-Headers",
//                "*");
//
//        System.out.println(request.getMethod());
//
//        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            System.out.println("option:");
//            System.out.println(response.getHeader("Access-Control-Allow-Origin"));
//            response.setStatus(HttpServletResponse.SC_OK);
//        }else {
//            System.out.println("getr:");
//            System.out.println(response.getHeader("Access-Control-Allow-Origin"));
//            chain.doFilter(req, response);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}