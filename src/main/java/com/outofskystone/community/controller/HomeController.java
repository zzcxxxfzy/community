package com.outofskystone.community.controller;

import com.outofskystone.community.dao.DiscussPostMapper;
import com.outofskystone.community.entity.DiscussPost;
import com.outofskystone.community.entity.Page;
import com.outofskystone.community.entity.User;
import com.outofskystone.community.service.DiscussPostService;
import com.outofskystone.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscussPostService discussPostService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        //springmvc会自动实例化Model和Page，并且将Page注入Model.
        //前端可以用page直接访问Page对象中的数据。
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }

//        for (Map<String, Object> discussPost : discussPosts) {
//            System.out.println(discussPost.get("post"));
//            System.out.println(discussPost.get("user"));
//        }

        model.addAttribute("discussPosts", discussPosts);
        return "/index";
    }
}
