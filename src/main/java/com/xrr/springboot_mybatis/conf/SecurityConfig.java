package com.xrr.springboot_mybatis.conf;

import com.xrr.springboot_mybatis.authentication.DemoAuthenticationFailureHandler;
import com.xrr.springboot_mybatis.authentication.DemoAuthenticationSuccessHandler;
import com.xrr.springboot_mybatis.filter.SmsCodeFilter;
import com.xrr.springboot_mybatis.filter.ValidateCodeFilter;
import com.xrr.springboot_mybatis.properties.CodeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author: xue.rongrong
 * @Date: 2019/5/22 16:02
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DemoAuthenticationSuccessHandler demoAuthenticationSuccessHandler;
    @Autowired
    private DemoAuthenticationFailureHandler demoAuthenticationFailureHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CodeProperties codeProperties;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setDemoAuthenticationFailureHandler(demoAuthenticationFailureHandler);

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setDemoAuthenticationFailureHandler(demoAuthenticationFailureHandler);

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()//表示使用form表单提交
                .loginPage("/login.html")//我们定义的登录页
                .loginProcessingUrl("/authentication/form")//因为SpringSecurity默认是login请求为登录请求，所以需要配置自己的请求路径
                .successHandler(demoAuthenticationSuccessHandler)//登录成功的操作
                .failureHandler(demoAuthenticationFailureHandler)//登录失败的操作
                    .and()
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(codeProperties.getRememberMeSeconds())
                .and()
                .authorizeRequests()//对请求进行授权
                .antMatchers("/login.html","/code/*").permitAll()//表示login.html路径不会被拦截
                .anyRequest()//表示所有请求
                .authenticated()//需要权限认证
//                .userDetailsService(userDetailsService)
                .and()
                .csrf().disable()//这是SpringSecurity的安全控制，我们这里先关掉
                .apply(smsCodeAuthenticationSecurityConfig);//添加手机号验证码校验
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true); 这个操作是帮我们自定创建一张表，只需操作一次，当数据库这张表已经存在的时候，会报错
        //我们在数据库手动创建
//        create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)
        return tokenRepository;
    }
}
