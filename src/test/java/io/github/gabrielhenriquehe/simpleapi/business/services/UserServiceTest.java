package io.github.gabrielhenriquehe.simpleapi.business.services;

import io.github.gabrielhenriquehe.simpleapi.business.repositories.UserRepository;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in.CreateUserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    class CreateUser {

        @Test
        @DisplayName("Should create an user successfully.")
        void shouldCreateUserSuccessfully() {

            // Arrange
            var user = new User(
                1L,
                "gabriel",
                "gabriel@email.com",
                "123",
                 Instant.now(),
                 Instant.now()
            );
            // Retorna uma instância de User quando save for invocado.
            doReturn(user).when(userRepository).save(any());

            var input = new CreateUserDTO(
                    "gabriel",
                    "gabriel@email.com",
                    "123"
            );

            // Act
            // Invoca createUser, onde o save será invocado, fazendo com que retorne um User.
            var output = userService.createUser(input);

            // Assert
            // Certifica que a saída gerada por createUser não é nula.
            assertNotNull(output);
        }

        @Test
        @DisplayName("Should throw an exception when error occurs.")
        void shouldThrowExceptionWhenErrorOccurs() {

            // Arrange
            // Lança uma exceção quando save for invocado.
            doThrow(new RuntimeException()).when(userRepository).save(any());
            var input = new CreateUserDTO(
                    "gabriel",
                    "gabriel@email.com",
                    "123"
            );

            // Act & Assert
            // Certifica que a exceção será lançada quando createUser invocar o save.
            assertThrows(RuntimeException.class, () -> userService.createUser(input));
        }
    }

}