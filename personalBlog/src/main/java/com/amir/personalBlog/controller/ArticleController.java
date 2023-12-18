package com.amir.personalBlog.controller;

import com.amir.personalBlog.entity.Article;
import com.amir.personalBlog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String showAllArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "admin/articles";
    }

    @GetMapping("/create")
    public String showCreateArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "admin/create-article";
    }

    @PostMapping("/create")
    public String createArticle(@ModelAttribute("article") Article article) {
        articleService.saveArticle(article);
        return "redirect:/admin/articles";
    }

    @PutMapping("/edit/{id}")
    public String showEditArticleForm(@PathVariable Long id, Model model) {
        Article article = articleService.getArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        model.addAttribute("article", article);
        return "admin/edit-article";
    }

    @PostMapping("/edit/{id}")
    public String editArticle(@PathVariable Long id, @ModelAttribute("article") Article article) {
        article.setId(id);
        articleService.saveArticle(article);
        return "redirect:/admin/articles";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/admin/articles";
    }
}
