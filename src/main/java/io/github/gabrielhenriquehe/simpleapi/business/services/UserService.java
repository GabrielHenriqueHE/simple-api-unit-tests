package io.github.gabrielhenriquehe.simpleapi.business.services;

import io.github.gabrielhenriquehe.simpleapi.business.repositories.UserRepository;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in.CreateUserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in.UpdateUserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.out.UserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.entities.User;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.exceptions.ResourceNotFoundException;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(CreateUserDTO dto) {

        User user = this.userRepository.save(
                UserMapper.DTOToEntity(dto)
        );

        return UserMapper.entityToDTO(user);
    }

    public List<UserDTO> findAllUsers() {
        List<User> rawUsers = this.userRepository.findAll();

        return rawUsers.stream().map(UserMapper::entityToDTO).toList();
    }

    public UserDTO updateUser(Long userId, UpdateUserDTO dto) {

        var optionalUser = this.userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found from ID: ".concat(String.valueOf(userId)));
        }

        User user = optionalUser.get();

        if (dto.username() != null) {
            user.setUsername(dto.username());
        }

        if (dto.password() != null) {
            user.setPassword(dto.password());
        }

        user = this.userRepository.save(user);

        return UserMapper.entityToDTO(user);
    }

    public void deleteUser(Long userId) {
        var optionalUser = this.userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found from ID: ".concat(String.valueOf(userId)));
        }

        User user = optionalUser.get();

        this.userRepository.delete(user);
    }

}
