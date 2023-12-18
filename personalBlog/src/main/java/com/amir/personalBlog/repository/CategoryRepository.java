package com.amir.personalBlog.repository;

import com.amir.personalBlog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Özel sorgular veya operasyonlar buraya eklenebilir.
}
