package com.jzz.webdemo.controller;

import com.jzz.webdemo.service.ArticleService;
import com.jzz.webdemo.utl.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")
    public Msg getList() {
        return articleService.getList();
    }

    @RequestMapping("/add")
    public Msg add(@RequestParam("title") String title,
                   @RequestParam("subtitle") String subtitle,
                   @RequestParam("content") String content,
                   @RequestParam("authorId") int authorId) {
        return articleService.add(title, subtitle, content, authorId);
    }

    @RequestMapping("/remove")
    public Msg remove(@RequestParam("id") int id) {
        return articleService.remove(id);
    }

    @RequestMapping("/get")
    public Msg get(@RequestParam("id") int id) {
        return articleService.get(id);
    }

}
