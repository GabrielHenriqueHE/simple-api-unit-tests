package io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in;

import lombok.NonNull;

public record CreateUserDTO(
        @NonNull String username,
        @NonNull String email,
        @NonNull String password
) {
}
