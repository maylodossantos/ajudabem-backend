package com.ajudabem.backend.dto.news.response;

public record ResponseNewsDTO(Long id, String title, String subtitle, String content, String city, String country, String created_at, String last_update, Long user_id) {
}