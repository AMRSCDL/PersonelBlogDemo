package com.amir.personalBlog.repository;

import com.amir.personalBlog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // Özel sorgular veya operasyonlar buraya eklenebilir.
}