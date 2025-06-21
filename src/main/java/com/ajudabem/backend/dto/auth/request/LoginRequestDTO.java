package com.ajudabem.backend.dto.auth.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO (

        @NotNull
        String email,

        @NotNull
        String password) { }
