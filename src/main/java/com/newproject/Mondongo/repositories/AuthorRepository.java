package com.newproject.Mondongo.repositories;

import com.newproject.Mondongo.entities.Author;
import com.newproject.Mondongo.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByUniqueCode(String uniqueCode);

    List<Author> findAuthorByGender(Gender gender);
}
