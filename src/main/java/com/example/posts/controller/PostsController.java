package com.example.posts.controller;

import com.example.posts.model.Post;
import com.example.posts.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/posts")
public class PostsController {

  private final PostsService postsService;

  @Autowired
  public PostsController(PostsService postsService) {
    this.postsService = postsService;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Post post) {
    postsService.create(post);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Page<Post>> getAll(
      @RequestParam Optional<Integer> page,
      @RequestParam Optional<Integer> size
  ) {
    final Page<Post> postList = postsService.getAll(
        PageRequest.of(
            page.orElse(0),
            size.orElse(5),
            Sort.Direction.ASC, "id"
        ));

    return postList != null &&  !postList.isEmpty()
        ? new ResponseEntity<>(postList, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Post>> getById(@PathVariable(name = "id") int id) {
    final Optional<Post> post = postsService.getById(id);

    return post != null
        ? new ResponseEntity<>(post, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Post post) {
    final boolean updated = postsService.update(id, post);

    return updated
        ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
    final boolean deleted = postsService.delete(id);

    return deleted
        ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }
}
