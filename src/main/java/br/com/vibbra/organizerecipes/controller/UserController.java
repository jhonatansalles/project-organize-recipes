package br.com.vibbra.organizerecipes.controller;

import br.com.vibbra.organizerecipes.model.entity.users.Users;
import br.com.vibbra.organizerecipes.model.response.UserResponse;
import br.com.vibbra.organizerecipes.service.users.UsersService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('USER')")
public class UserController {

    @Autowired
    UsersService usersService;

    @ApiOperation(value = "Retrieve user data by ID.")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUser(id));
    }

    @ApiOperation(value = "Create a specific user with the data entered.")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> create(@RequestBody Users user) {
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change data for a specific user.")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Users user) {
        usersService.updateUser(id, user);
        return ResponseEntity.noContent().build();
    }
}