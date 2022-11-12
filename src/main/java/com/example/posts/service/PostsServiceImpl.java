package com.example.posts.service;

import com.example.posts.model.Post;
import com.example.posts.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService {

  @Autowired
  private PostsRepository postsRepository;

  @Override
  public void create(Post post) {
    post.setCreatedAt();
    postsRepository.save(post);
  }

  @Override
  public Page<Post> getAll(PageRequest req) {
    return postsRepository.findAll(req);
  }

  @Override
  public Optional<Post> getById(int id) {
    return postsRepository.findById(id);
  }

  @Override
  public boolean update(int id, Post post) {
    if (postsRepository.existsById(id)) {
      post.setId(id);
      postsRepository.save(post);
      return true;
    }
    return false;
  }

  @Override
  public boolean delete(int id) {
    if (postsRepository.existsById(id)) {
      postsRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
