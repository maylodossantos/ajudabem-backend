package com.ajudabem.backend.dto.news.request;

import jakarta.validation.constraints.NotNull;

public record RequestNewsDTO(

        @NotNull
        String title,
        String subtitle,
        @NotNull
        String content,
        @NotNull
        String city,
        @NotNull
        String country) { }