package com.Gogedit.controller;

import com.Gogedit.dto.post.CreatePostDto;
import com.Gogedit.persistence.entity.Post;
import com.Gogedit.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communities/{communityName}/posts")
public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@PathVariable String communityName, @RequestBody CreatePostDto createPostDto) {
        return postService.createPost(communityName, createPostDto);
    }
}
