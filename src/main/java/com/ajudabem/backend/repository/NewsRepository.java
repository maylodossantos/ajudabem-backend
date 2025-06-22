package com.ajudabem.backend.repository;

import com.ajudabem.backend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByDeletedFalse();

    List<News> findByUserId(Long userId);

}
