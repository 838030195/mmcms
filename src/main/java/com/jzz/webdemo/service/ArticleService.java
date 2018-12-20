package com.jzz.webdemo.service;

import com.jzz.webdemo.entity.Article;
import com.jzz.webdemo.entity.User;
import com.jzz.webdemo.mapper.ArticleMapper;
import com.jzz.webdemo.mapper.UserMapper;
import com.jzz.webdemo.utl.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    public Msg add(String title, String subtitle, String content, int authorId) {
        Article article = new Article();
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setContent(content);
        article.setAuthorId(authorId);
        article.setTime(System.currentTimeMillis());

        articleMapper.add(article);

        return Msg.ok("添加成功", article);
    }

    public Msg getList() {
        return Msg.ok("", articleMapper.getList());
    }

    public Msg get(int id) {
        return Msg.ok("", articleMapper.get(id));
    }

    public Msg remove(int id) {
        int res = articleMapper.remove(id);
        if (res > 0) {
            return Msg.ok("删除成功", null);
        } else {
            return Msg.err("删除失败");
        }
    }

    public ModelAndView info(int id) {
        Article article = articleMapper.get(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("info");

        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("subtitle", article.getSubtitle());
        modelAndView.addObject("time",
                new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(article.getTime()));
        modelAndView.addObject("content", article.getContent());

        User user = userMapper.get(article.getAuthorId());
        modelAndView.addObject("author", user.getUsername());
        return modelAndView;
    }
}
