package com.lin.blog;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ArticleRepository extends JpaRepository<Article,Long> { List<Article> findByPublishedTrueOrderByCreatedAtDesc(); Optional<Article> findBySlugAndPublishedTrue(String slug); }
