package com.ustb.controller;

import com.ustb.pojo.User;
import com.ustb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/findall")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<User> list = userService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("listview");
        return mv;
    }
}