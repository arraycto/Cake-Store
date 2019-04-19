package com.cake.shop.config;

import com.cake.shop.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor interceptor = new LoginInterceptor();

        //白名单
        ArrayList<String> list = new ArrayList<>();
        list.add("/javascriptapi/**");
        list.add("/user-definedAPI/**");

        list.add("/home_page.html");
        list.add("/allCakeInfo.html");
        list.add("/archonLogin.html");
        list.add("/cakeVariety.html");
        list.add("/register.html");
        list.add("/login.html");

        list.add("/modifyAmount.html");
        list.add("/ManageAssociator.HTML");
        list.add("/editing.HTML");
        list.add("/addNewCake.html");
        list.add("/operation.html");

        list.add("/angel_cake/**");

        list.add("/consuls/**");

        list.add("/customers/c_register");
        list.add("/customers/c_login");

        //注册拦截器
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);
    }
}
