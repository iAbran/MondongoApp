package com.newproject.Mondongo.repositories;

import com.newproject.Mondongo.entities.Client;
import com.newproject.Mondongo.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByPhoneNumber(String phoneNumber);

    List<Client> findByGender(Gender gender);
}
