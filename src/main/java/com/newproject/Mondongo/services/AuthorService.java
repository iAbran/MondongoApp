package com.newproject.Mondongo.services;

import com.newproject.Mondongo.dto.AuthorDTO;
import com.newproject.Mondongo.entities.Author;
import com.newproject.Mondongo.entities.Gender;
import com.newproject.Mondongo.exceptions.MyCustomApiException;
import com.newproject.Mondongo.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    //GET ALL AUTHORS
    public List<AuthorDTO> getAuthors() {
        return convertData(repository.findAll());
    }

    //GET AUTHOR BY ID
    public AuthorDTO getAuthorById(Long id) {
        return repository.findById(id)
                .map(a -> new AuthorDTO(
                        a.getId(),
                        a.getName(), a.getLastName(),
                        a.getAge(), a.getBirthdate(),
                        a.getGender(),a.getCourses()))
                .orElseThrow(() -> new MyCustomApiException("No author with id: "+id+" was found"));
    }

    //GET AUTHORS BY GENDER
    public List<AuthorDTO> getAuthorByGender(String text) {
        Gender gender = Gender.fromString(text);
        return convertData(repository.findAuthorByGender(gender));
    }

    //ADD NEW AUTHOR
    public void addNewAuthor(Author a){
        Optional<Author> authorOptional = repository.findByUniqueCode(a.getUniqueCode());
        if (authorOptional.isPresent()) {
            throw new MyCustomApiException("this unique code is already taken");
        }
        repository.save(a);
    }

    //DELETE AUTHOR BY ID
    public void deleteAuthor(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists) {
            throw new MyCustomApiException("this id "+id+" does not exists");
        }
        repository.deleteById(id);
    }

    //UPDATE AUTHORS VALUES
    @Transactional
    public void updateAuthor(Long id, String name, String lastName) {
        Author author = repository.findById(id).orElseThrow(() -> new MyCustomApiException("No client with id "+id+" was found"));

        //Update first name
        if (name != null && !name.isEmpty() && !Objects.equals(author.getName(), name)) {
            author.setName(name);
        }

        //Update last name
        if (lastName != null && !lastName.isEmpty() && !Objects.equals(author.getLastName(), lastName)) {
            author.setLastName(lastName);
        }
    }

    //Convert List of Author into List of AuthorDto
    private List<AuthorDTO> convertData(List<Author> authors) {
        return authors.stream()
                .map(a -> new AuthorDTO(a.getId(),
                        a.getName(), a.getLastName(),
                        a.getAge(), a.getBirthdate(),
                        a.getGender(),a.getCourses()))
                .collect(Collectors.toList());
    }
}