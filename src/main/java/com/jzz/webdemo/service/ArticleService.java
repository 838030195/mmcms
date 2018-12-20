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
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    public Msg add(String title, String subtitle, String content, int authorId, String source) {
        if (title.isEmpty() || content.isEmpty() || source.isEmpty()) {
            return Msg.err("无效的文章");
        }
        Article article = new Article();
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setContent(content);
        article.setAuthorId(authorId);
        article.setTime(System.currentTimeMillis());
        article.setSource(source);

        articleMapper.add(article);

        return Msg.ok("添加成功", article);
    }

    public Msg getList() {
        List<Article> list = articleMapper.getList();

        for (Article article : list) {
            article.setAuthor(userMapper.get(article.getAuthorId()).getUsername());
            article.setSource("");
        }

        return Msg.ok("", list);
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

    public Msg update(int id, String title, String subtitle, String content, String source) {
        if (title.isEmpty() || content.isEmpty() || source.isEmpty()) {
            return Msg.err("无效的文章");
        }

        Article article = articleMapper.get(id);

        if (article == null) {
            return Msg.err("无效的文章");
        }
        article.setTitle(title);
        article.setSubtitle(subtitle);
        article.setContent(content);
        article.setSource(source);
        int res = articleMapper.update(article);
        if (res > 0) {
            return Msg.ok("修改成功", article);
        } else {
            return Msg.err("修改失败");
        }
    }

    public ModelAndView info(int id) {
        Article article = articleMapper.get(id);

        if (article == null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            return modelAndView;
        } else {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("info");

            modelAndView.addObject("title", article.getTitle());
            modelAndView.addObject("subtitle", article.getSubtitle());
            modelAndView.addObject("time",
                    new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(article.getTime()));
            modelAndView.addObject("content", article.getContent());

            User user = userMapper.get(article.getAuthorId());
            modelAndView.addObject("author", user.getUsername());

            modelAndView.addObject("href", "http://localhost/modify/" + article.getId());
            return modelAndView;
        }

    }

    public ModelAndView modify(int id) {
        Article article = articleMapper.get(id);

        if (article == null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            return modelAndView;
        } else {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("modify");

            modelAndView.addObject("title", article.getTitle());
            modelAndView.addObject("subtitle", article.getSubtitle());
            modelAndView.addObject("id", article.getId());
            modelAndView.addObject("content", article.getContent());
            modelAndView.addObject("source", article.getSource());

            return modelAndView;
        }

    }
}
