package io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.out;

import java.time.Instant;

public record UserDTO(
        String username,
        String email,
        Instant createTimestamp,
        Instant updateTimestamp
) {
}
