package com.BlogSphereProject.blogSphere.Controller;

import com.BlogSphereProject.blogSphere.Models.BlogEntities;
import com.BlogSphereProject.blogSphere.ServiceLayer.BlogService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin
public class BlogSphereController {
    private final BlogService blogService;

    public BlogSphereController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public BlogEntities addBlog(
            @RequestPart("title") String title,
            @RequestPart("description") String description,
            @RequestPart("category") String category,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        return blogService.addBlog(title, description, category, image);
    }

    @GetMapping
    public List<BlogEntities> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/category/{category}")
    public List<BlogEntities> getBlogsByCategory(@PathVariable String category) {
        return blogService.getBlogsByCategory(category);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getBlogImage(@PathVariable Long id) {

        BlogEntities blog = blogService.getBlogById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, blog.getImageType())
                .body(blog.getImageData());
    }
}
