package com.ustb.controller;

import com.ustb.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/findall")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        User user1 = new User(1, "张三", 15);
        User user2 = new User(2, "李四", 16);
        User user3 = new User(3, "王二", 17);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        //设置视图
        mv.setViewName("userList");
        //数据
        mv.addObject("userList",list);
        return mv;
    }
}