package com.BlogSphereProject.blogSphere.ServiceLayer;

import com.BlogSphereProject.blogSphere.Models.BlogEntities;
import com.BlogSphereProject.blogSphere.Repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class BlogService {
    private final BlogRepository repo;

    public BlogService(BlogRepository repo) {
        this.repo = repo;
    }

    public BlogEntities addBlog(
            String title,
            String description,
            String category,
            MultipartFile image
    ) throws IOException {

        BlogEntities blog = new BlogEntities();
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setCategory(category);
        blog.setImageName(image.getOriginalFilename());
        blog.setImageType(image.getContentType());
        blog.setImageData(image.getBytes());
        System.out.println("Image bytes length: " + image.getBytes().length);


        return repo.save(blog);
    }

    public List<BlogEntities> getAllBlogs() {
        return repo.findAll();
    }

    public List<BlogEntities> getBlogsByCategory(String category) {
        return repo.findByCategoryIgnoreCase(category);
    }

    public BlogEntities getBlogById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }
}
