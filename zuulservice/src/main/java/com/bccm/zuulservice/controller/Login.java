package com.bccm.zuulservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Api(value = "Zuul")
@RestController
public class Login {


    @ApiOperation(value = "登录失败")
    @RequestMapping(value = "/loginFail",produces = { "application/json;charset=UTF-8" })
    public String loginFail(){
        return "login fail!";
    }

    @ApiOperation(value = "跳转登录")
    @RequestMapping(value = "/needLogin",produces = { "application/json;charset=UTF-8" })
    public void needLogin(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("{\"status\":\"error\",\"msg\":\"尚未登录，请登录!\"}");
        out.flush();
        out.close();
    }

}
