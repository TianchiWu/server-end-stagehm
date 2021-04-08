package com.example.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {
    // 控制层不要直接调用业务层，要抽象一个接口
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login.html")
    public String loginPage() {
        return "login";
    }// 如果是一个String，会认为是一个视图名

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, @Valid LoginInfo loginInfo, Errors errors) {
        if (errors.hasErrors()) {
            StringBuilder str = new StringBuilder();
            for(ObjectError error : errors.getAllErrors()) {
                str.append(error.getDefaultMessage()).append("\n");
            }
            return new ModelAndView("login", "error", str.toString());
        }
        boolean isValidUser =
                userService.hasMatchUser(loginInfo.getUserName(),
                        loginInfo.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginInfo
                    .getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.saveLog(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }
}
