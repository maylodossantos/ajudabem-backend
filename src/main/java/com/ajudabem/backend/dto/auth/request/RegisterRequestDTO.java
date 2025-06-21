package com.ajudabem.backend.dto.auth.request;

import jakarta.validation.constraints.NotNull;

public record RegisterRequestDTO (

        @NotNull
        String name,

        @NotNull
        String email,

        String cpf,

        @NotNull
        String password){
}