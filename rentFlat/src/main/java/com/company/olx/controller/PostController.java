package com.company.olx.controller;

import com.company.olx.dto.post.PostDTO;
import com.company.olx.dto.post.PostFilterDTO;
import com.company.olx.enums.ProfileRole;
import com.company.olx.service.PostService;
import com.company.olx.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostDTO dto){
        return ResponseEntity.ok(postService.creat(dto));
    }

    @GetMapping("/pageFilter")
    public ResponseEntity<?> getAllPagingFilter(@RequestParam("page") int page,
                                                @RequestParam("size") int size,
                                                PostFilterDTO dto){
        return ResponseEntity.ok(postService.filter(page,size,dto));
    }

    @GetMapping("/pageFilterCriteria")
    public void pageFilterCriteria(@RequestParam("page") int page,
                                   @RequestParam("size") int size,
                                   PostFilterDTO dto){
        postService.filterCriteriaBuilder(page,size,dto);
    }



}
