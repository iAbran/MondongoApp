package com.newproject.Mondongo.controllers;

import com.newproject.Mondongo.dto.AuthorDTO;
import com.newproject.Mondongo.entities.Author;
import com.newproject.Mondongo.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inicio")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    //GET ALL
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/author")
    public List<AuthorDTO> getAuthors() {
        return service.getAuthors();
    }

    //GET BY ID
    @GetMapping(path = "/author/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id) {
        return service.getAuthorById(id);
    }

    //GET BY GENDER
    @GetMapping(path = "/author/gender/{text}")
    public List<AuthorDTO> getAuthorByGender(@PathVariable String text) {
        return service.getAuthorByGender(text);
    }

    //POST
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(path = "/author/api")
    public void addNewAuthor(@RequestBody Author a) {
        service.addNewAuthor(a);
    }

    //DELETE
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping(path = "/author/delete/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        service.deleteAuthor(id);
    }

    //PUT
    @PutMapping(path = "/author/update/{id}")
    public void updateAuthor(@PathVariable("id") Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String lastName) {
        service.updateAuthor(id, name, lastName);
    }
}