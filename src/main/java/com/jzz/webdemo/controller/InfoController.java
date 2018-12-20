package com.jzz.webdemo.controller;

import com.jzz.webdemo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/info/{id}")
    public ModelAndView info(@PathVariable("id") int id) {
        return articleService.info(id);
    }

    @RequestMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") int id) {
        return articleService.modify(id);
    }
}
