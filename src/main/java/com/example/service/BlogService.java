package com.example.service;

import java.util.List;

import com.example.domain.Blog;
import com.example.domain.Post;
import com.example.mappers.BlogMapper;
import com.example.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class BlogService {

    public void insertBlog(Blog blog) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            blogMapper.insertBlog(blog);
            sqlSession.commit();
        }
    }

    public Blog getBlogById(Integer blogId) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            return blogMapper.getBlogById(blogId);
        }
    }

    public List<Blog> getAllBlogs() {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            return blogMapper.getAllBlogs();
        }
    }

    public void updateBlog(Blog blog) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            blogMapper.updateBlog(blog);
            sqlSession.commit();
        }
    }

    public void deleteBlog(Integer blogId) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            blogMapper.deleteBlog(blogId);
            sqlSession.commit();
        }

    }

    public void deleteAllBlogs() {
        for (Blog blog : getAllBlogs()) {
            deleteBlog(blog.getBlogId());
        }
    }
}
