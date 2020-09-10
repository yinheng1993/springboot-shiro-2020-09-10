package com.yinheng.controller;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author yinheng
 * @create 2020/9/2 21:37
 * @description:
 */
@Controller
public class UserController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello world");
        return "index";
    }

    /**
     *  @author: yinheng
     *  @description:测试thymeleaf集成
     *  @date:  2020/9/2 21:50
     */
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name","殷恒");
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        System.out.println("add。。。。。。");
        return "add";
    }
    @RequestMapping("/update")
    public String update(){
        System.out.println("update。。。。。。");
        return "update";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "toLogin";
    }
    @RequestMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }

    @PostMapping("/login")
    public String login(String name,String password,Model model){
        //使用shiro编写认证操作
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        //3.执行登录方法
        try {
            subject.login(usernamePasswordToken);
            //登录成功
            //重定向访问路径
            return "redirect:/testThymeleaf";
        }
//        catch (AuthenticationException e) {
//            e.printStackTrace();
//            model.addAttribute("用户名或密码错误");
//            return "login";
//        }
        //可进行更详细的判断：账号错误还是密码错误
        catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("error","用户名错误");
            return "toLogin";
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("error","密码错误");
            return "toLogin";
        }

    }
}
