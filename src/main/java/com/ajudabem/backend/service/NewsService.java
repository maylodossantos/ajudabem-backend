package com.ajudabem.backend.service;

import com.ajudabem.backend.dto.news.request.RequestNewsDTO;
import com.ajudabem.backend.dto.news.response.ResponseNewsDTO;
import com.ajudabem.backend.model.News;
import com.ajudabem.backend.model.User;
import com.ajudabem.backend.repository.NewsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<ResponseNewsDTO> getNews () {
        var allNews = newsRepository.findAllByDeletedFalse();
        return allNews.stream().map(news -> new ResponseNewsDTO(
                news.getId(),
                news.getTitle(),
                news.getSubtitle(),
                news.getContent(),
                news.getCity(),
                news.getCountry(),
                news.getCreated_at().toString(),
                news.getLast_update() == null ? null :  news.getLast_update().toString(),
                news.getUser().getId()
        )).toList();
    }

    public List<ResponseNewsDTO> getNewsByUserId (Long id) {
        var allNews = newsRepository.findByUserId(id);
        return allNews.stream().map(news -> new ResponseNewsDTO(
                news.getId(),
                news.getTitle(),
                news.getSubtitle(),
                news.getContent(),
                news.getCity(),
                news.getCountry(),
                news.getCreated_at().toString(),
                news.getLast_update() == null ? null :  news.getLast_update().toString(),
                news.getUser().getId()
        )).toList();
    }

    @Transactional
    public ResponseEntity postNews (RequestNewsDTO data) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getId() != null) {
            News newNews = new News(user, data);

            newsRepository.save(newNews);

            return ResponseEntity.ok(new ResponseNewsDTO(
                    newNews.getId(),
                    newNews.getTitle(),
                    newNews.getSubtitle(),
                    newNews.getContent(),
                    newNews.getCity(),
                    newNews.getCountry(),
                    newNews.getCreated_at().toString(),
                    newNews.getLast_update() == null ? null :  newNews.getLast_update().toString(),
                    user.getId()
            ));

        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Usuário não encontrado.");
        }
    }

    @Transactional
    public ResponseEntity updateNews (RequestNewsDTO data, Long id) {
        Optional<News> optionalNews = newsRepository.findById(id);

        if  (optionalNews.isPresent()) {
            News newNews = optionalNews.get();

            newNews.setTitle(data.title());
            newNews.setSubtitle(data.subtitle());
            newNews.setContent(data.content());
            newNews.setCity(data.city());
            newNews.setCountry(data.country());

            return ResponseEntity.ok(newsRepository.save(newNews));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Transactional
    public ResponseEntity deleteNews (Long id) {
        Optional<News> optionalNews = newsRepository.findById(id);

        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            news.setDeleted(true);
            return ResponseEntity.ok("Produto id: " + id + " deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
