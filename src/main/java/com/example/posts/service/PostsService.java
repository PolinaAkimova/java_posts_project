package com.example.posts.service;

import com.example.posts.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface PostsService {
  void create(Post post);

  Page<Post> getAll(PageRequest id);

  Optional<Post> getById(int id);

  boolean update(int id, Post post);

  boolean delete(int id);
}
