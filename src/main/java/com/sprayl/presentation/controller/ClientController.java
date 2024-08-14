package com.sprayl.presentation.controller;

import com.sprayl.application.service.ClientService;
import com.sprayl.infrastructure.persistence.dto.ClientDto;
import com.sprayl.infrastructure.persistence.dto.ClientSavedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @package : com.sprayl.presentation.controller
 * @name : ClientController.java
 * @date : 2024-08
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@PreAuthorize("denyAll()")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public @RestController class ClientController {

    private final ClientService clientService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<ClientSavedDto>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.clientService.findAll(pageable).join());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ClientSavedDto> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.clientService.findById(id).join());
    }


    @PreAuthorize("permitAll()")
    @PostMapping
    public HttpStatus save(@RequestBody ClientDto clientDto) {

        this.clientService.save(clientDto).join();

        return HttpStatus.CREATED;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public HttpStatus update(@PathVariable(name = "id") Long id, @RequestBody ClientDto clientDto) {
        this.clientService.update(id, clientDto).join();
        return HttpStatus.OK;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        this.clientService.delete(id).join();
        return ResponseEntity.ok("Client deleted successfully");
    }
}
