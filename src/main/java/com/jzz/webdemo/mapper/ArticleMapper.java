package com.jzz.webdemo.mapper;

import com.jzz.webdemo.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO `article` (`title`, `subtitle`, `content`, `time`, `author_id`) VALUES (" +
            "#{title}, #{subtitle}, #{content}, #{time}, #{authorId})")
    int add(Article article);

    @Select("SELECT * FROM `article` WHERE `id`=#{id}")
    Article get(@Param("id") int id);

    @Select("SELECT * FROM `article` ORDER BY `id` DESC")
    List<Article> getList();

    @Update("UPDATE `article` SET `title`=#{title}, `subtitle`=#{subtitle}, `content`=#{content} WHERE `id`=#{id} LIMIT 1")
    int update(Article article);

    @Delete("DELETE FROM `article` WHERE `id`=#{id} LIMIT 1")
    int remove(@Param("id") int id);
}
