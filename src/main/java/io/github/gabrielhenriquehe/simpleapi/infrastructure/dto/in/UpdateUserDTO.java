package io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in;

public record UpdateUserDTO(
        String username,
        String password
) {
}
