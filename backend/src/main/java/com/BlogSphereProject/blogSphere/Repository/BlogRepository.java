package com.BlogSphereProject.blogSphere.Repository;

import com.BlogSphereProject.blogSphere.Models.BlogEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogRepository extends JpaRepository<BlogEntities, Long> {
    List<BlogEntities> findByCategoryIgnoreCase(String category);
}
