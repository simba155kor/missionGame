//package com.simba.missonGame.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/**")
//                .antMatchers(HttpMethod.OPTIONS)
//                .antMatchers(HttpMethod.GET)
//        ;
//    }
//
////    @Bean
////    public FilterRegistrationBean processCorsFilter() {
////        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        final CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        config.addAllowedOrigin("'");
////        config.addAllowedHeader("*");
////        config.addAllowedMethod("*");
////        source.registerCorsConfiguration("/**", config);
////
////
////        final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
////        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
////        return bean;
////    }
//
////    @Bean
////    public CorsFilter corsFilter() {
////        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        final CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowCredentials(false);
////        configuration.addAllowedOrigin("*");
////        configuration.addAllowedHeader("*");
////        configuration.addAllowedMethod("OPTIONS");
////        configuration.addAllowedMethod("HEAD");
////        configuration.addAllowedMethod("GET");
////        configuration.addAllowedMethod("PUT");
////        configuration.addAllowedMethod("POST");
////        configuration.addAllowedMethod("DELETE");
////        configuration.addAllowedMethod("PATCH");
////        source.registerCorsConfiguration("/**", configuration);
////        return new CorsFilter(source);
////    }
//
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource(){
////        CorsConfiguration configuration = new CorsConfiguration();
////
////        configuration.addAllowedOriginPattern("*");
////        configuration.addAllowedHeader("*");
////        configuration.addAllowedMethod("*");
////        configuration.setAllowCredentials(true);
////
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////
////        return source;
////    }
//
//
//}
