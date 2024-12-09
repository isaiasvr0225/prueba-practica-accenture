package com.accenture.presentation.controller.user;

import com.accenture.application.service.user.UserService;
import com.accenture.infrastructure.persistence.dto.user.UserRequestDTO;
import com.accenture.infrastructure.persistence.dto.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @package : com.sprayl.presentation.controller
 * @name : UserController.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v1/clients")
public @RestController class UserController {

    private final UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.userService.findAll(pageable).join());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.userService.findById(id).join());
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public HttpStatus save(@RequestBody UserRequestDTO userRequestDTO) {

        return this.userService.save(userRequestDTO).join();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public HttpStatus update(@PathVariable(name = "id") Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return this.userService.update(id, userRequestDTO).join();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable(name = "id") Long id) {
        return this.userService.delete(id).join();
    }
}
