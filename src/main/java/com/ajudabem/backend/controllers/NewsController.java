package com.ajudabem.backend.controllers;

import com.ajudabem.backend.dto.news.request.RequestNewsDTO;
import com.ajudabem.backend.dto.news.response.ResponseNewsDTO;
import com.ajudabem.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<ResponseNewsDTO> getAllNews() {
        return newsService.getNews();
    }

    @GetMapping("/users/{id}")
    public List<ResponseNewsDTO> getUserNews(@PathVariable Long id) {
        return newsService.getNewsByUserId(id);
    }

    @PostMapping
    public ResponseEntity createNews(@RequestBody @Validated RequestNewsDTO data) {
        ResponseEntity postNews = newsService.postNews(data);
        return ResponseEntity.ok(postNews);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNews(@PathVariable Long id, @RequestBody @Validated RequestNewsDTO data) {
        ResponseEntity updateNews = newsService.updateNews(data, id);
        return ResponseEntity.ok(updateNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNews(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.deleteNews(id));
    }

}
