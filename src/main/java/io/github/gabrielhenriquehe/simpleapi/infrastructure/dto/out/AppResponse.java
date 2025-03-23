package io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.out;

public record AppResponse<T>(
        String message,
        Integer code,
        T data
) {
}
