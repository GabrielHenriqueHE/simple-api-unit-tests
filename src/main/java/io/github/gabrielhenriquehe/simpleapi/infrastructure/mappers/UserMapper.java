package io.github.gabrielhenriquehe.simpleapi.infrastructure.mappers;

import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in.CreateUserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.out.UserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.entities.User;

public class UserMapper {

    public static User DTOToEntity(CreateUserDTO dto) {
        return new User(
            dto.username(),
            dto.email(),
            dto.password());
    }

    public static UserDTO entityToDTO(User user) {
        return new UserDTO(
            user.getUsername(),
            user.getEmail(),
            user.getCreateTimestamp(),
            user.getUpdateTimestamp()
        );
    }

}
