package com.example.service;

import java.util.Date;
import java.util.List;

import com.example.domain.Blog;
import com.example.domain.Post;
import com.example.mappers.BlogMapper;
import com.example.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BlogServiceTest {
    private static BlogService blogService;

    @BeforeClass
    public static void setup() {
        blogService = new BlogService();
    }

    @AfterClass
    public static void teardown() {
//        blogService.deleteAllBlogs();
        blogService = null;
    }

    @Test
    public void testGetBlogById() {
        int blogId = insertBlog();

        Blog blog = blogService.getBlogById(blogId);
        Assert.assertNotNull(blog);
        System.out.println(blog);
    }

    @Test
    public void getBlogById() {
        Blog blog = blogService.getBlogById(1);
        System.out.println(blog);
        List<Post> posts = blog.getPosts();
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    public void testGetAllBlogs() {
        for (int i = 0; i < 5; i++) {
            insertBlog();
        }

        List<Blog> blogs = blogService.getAllBlogs();
        Assert.assertNotNull(blogs);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }

    }

    @Test
    public void testInsertBlog() {
        Blog blog = new Blog();
        blog.setBlogName("test_blog_" + System.currentTimeMillis());
        blog.setCreatedOn(new Date());

        blogService.insertBlog(blog);
        Assert.assertTrue(blog.getBlogId() != 0);
        Blog createdBlog = blogService.getBlogById(blog.getBlogId());
        Assert.assertNotNull(createdBlog);
        Assert.assertEquals(blog.getBlogName(), createdBlog.getBlogName());

    }

    @Test
    public void testUpdateBlog() {
        int blogId = insertBlog();

        long timestamp = System.currentTimeMillis();
        Blog blog = blogService.getBlogById(blogId);
        blog.setBlogName("TestBlogName" + timestamp);
        blogService.updateBlog(blog);
        Blog updatedBlog = blogService.getBlogById(blogId);
        Assert.assertEquals(blog.getBlogName(), updatedBlog.getBlogName());
    }

    @Test
    public void testDeleteBlog() {
        int blogId = insertBlog();

        Blog blog = blogService.getBlogById(blogId);
        blogService.deleteBlog(blog.getBlogId());
        Blog deletedBlog = blogService.getBlogById(blogId);
        Assert.assertNull(deletedBlog);
    }

    private int insertBlog() {
        Blog blog = new Blog();
        blog.setBlogName("test_blog_" + System.currentTimeMillis());
        blog.setCreatedOn(new Date());

        blogService.insertBlog(blog);
        int blogId = blog.getBlogId();
        Assert.assertTrue(blogId != 0);
        Blog createdBlog = blogService.getBlogById(blog.getBlogId());
        Assert.assertNotNull(createdBlog);
        return blogId;
    }
}