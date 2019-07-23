package com.bccm.zuulservice.config;

import com.bccm.zuulservice.handler.AuthenticationAccessDeniedHandler;
import com.bccm.zuulservice.provider.UserAuthenticationProvider;
import com.bccm.zuulservice.service.UrlAccessDecisionManager;
import com.bccm.zuulservice.service.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationProvider provider;


    @Autowired
    UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                //.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()// 解决preflight跨域
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and().formLogin().loginPage("/needLogin").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").permitAll().failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                StringBuffer sb = new StringBuffer();
                sb.append("{\"status\":\"error\",\"msg\":\"");
                if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                    sb.append("用户名或密码输入错误，登录失败!");
                } else if (e instanceof DisabledException) {
                    sb.append("账户被禁用，登录失败，请联系管理员!");
                } else {
                    sb.append("登录失败!");
                }
                sb.append("\"}");
                out.write(sb.toString());
                out.flush();
                out.close();
            }
        }).successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                UserDetails user = (UserDetails)authentication.getPrincipal();

                //objectMapper objectMapper = new ObjectMapper();
                //String s = "{\"status\":\"success\",\"msg\":" + objectMapper.writeValueAsString(HrUtils.getCurrentHr()) + "}";
                String s = "{\"status\":\"success\",\"userName\":\"" +user.getUsername() + "\"}";
                out.write(s);
                out.flush();
                out.close();
            }
        }).and().logout().permitAll().and().cors().and().csrf().disable().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //将验证过程交给自定义验证工具
        auth.authenticationProvider(provider);
        //auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());//密码加盐.passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**");
    }

}
