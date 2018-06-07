package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

//安全配置类
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "fonts/**", "/index").permitAll()
                .antMatchers("/users/**").hasRole("ADMIN")  //只有admin才能访问users／
                .and()
                .formLogin()//基于form表单登录验证
                .loginPage("/login").failureUrl("/login-error");
        http.csrf().disable();
//        http
//                .logout()
//                .logoutUrl("／logout11")
//                .logoutSuccessUrl("/logout11")
//                .invalidateHttpSession(true);
    }


    //    认证信息管理
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()//认证信息存储在内存中
                .withUser("zz").password("123456").roles("ADMIN");
    }


}
