package io.github.gabrielhenriquehe.simpleapi.business.services;

import io.github.gabrielhenriquehe.simpleapi.business.repositories.UserRepository;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in.CreateUserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.entities.User;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor ArgumentCaptor<Long> longArgumentCaptor;

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
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

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

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
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

    @Nested
    class FindUserById {

        @Test
        @DisplayName("Should successfully find user by ID.")
        void shouldSuccessfullyFindUserById() {

            // Arrange
            var user = new User(
                    1L,
                    "gabriel",
                    "gabriel@email.com",
                    "123",
                    Instant.now(),
                    Instant.now()
            );
            doReturn(Optional.of(user)).when(userRepository).findById(longArgumentCaptor.capture());

            // Act
            var output = userService.findUserById(user.getId());

            // Assert
            assertNotNull(output);
        }

        @Test
        @DisplayName("Should throw an exception when user not found.")
        void shouldThrowExceptionWhenUserNotFound() {

            // Arrange
            Long input = 1L;
            when(userRepository.findById(input)).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(ResourceNotFoundException.class, () -> userService.findUserById(input));
        }
    }

    @Nested
    class FindAllUsers {

        @Test
        @DisplayName("Should return all users successfully.")
        void shouldReturnAllUsersSuccessfully() {

            // Arrange
            var user = new User(
                    1L,
                    "gabriel",
                    "gabriel@email.com",
                    "123",
                    Instant.now(),
                    Instant.now()
            );
            var userList = List.of(user);
            doReturn(userList).when(userRepository).findAll();

            // Act
            var output = userService.findAllUsers();
            // Assert

            assertNotNull(output);
            assertEquals(userList.size(), output.size());
        }

    }

}