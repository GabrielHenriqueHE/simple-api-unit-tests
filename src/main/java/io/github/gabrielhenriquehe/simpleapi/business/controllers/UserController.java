package io.github.gabrielhenriquehe.simpleapi.business.controllers;

import io.github.gabrielhenriquehe.simpleapi.business.services.UserService;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in.CreateUserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.in.UpdateUserDTO;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.out.AppResponse;
import io.github.gabrielhenriquehe.simpleapi.infrastructure.dto.out.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<AppResponse<UserDTO>> createUser(@RequestBody CreateUserDTO dto) {
        var user = this.userService.createUser(dto);

        AppResponse<UserDTO> response = new AppResponse<>(
                "User created successfully",
                HttpStatus.CREATED.value(),
                user
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<AppResponse<List<UserDTO>>> findAllUsers() {
        var users = this.userService.findAllUsers();

        AppResponse<List<UserDTO>> response = new AppResponse<List<UserDTO>>(
                null,
                HttpStatus.OK.value(),
                users
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AppResponse<UserDTO>> updateUser(@PathVariable("userId") Long userId,
                                                           @RequestBody UpdateUserDTO dto){
        var user = this.userService.updateUser(userId, dto);

        AppResponse<UserDTO> response = new AppResponse<>(
                "User updated sucessfully",
                HttpStatus.OK.value(),
                user
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<AppResponse<Void>> deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);

        AppResponse<Void> response = new AppResponse<>(
                "User deleted successfully",
                HttpStatus.OK.value(),
                null
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
