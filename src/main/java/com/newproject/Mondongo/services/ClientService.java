package com.newproject.Mondongo.services;

import com.newproject.Mondongo.dto.ClientDTO;
import com.newproject.Mondongo.entities.*;
import com.newproject.Mondongo.exceptions.MyCustomApiException;
import com.newproject.Mondongo.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    //GET ALL CLIENTS
    public List<ClientDTO> getClients() {
        return convertData(repository.findAll());
    }

    //GET CLIENT BY ID
    public ClientDTO getClientById(Long id) {
        return repository.findById(id).map(c -> new ClientDTO(
                        c.getId(), c.getName(), c.getLastName(),
                        c.getAge(), c.getBirthdate(),
                        c.getGender(), c.getPhoneNumber(),
                        c.getEmail(), c.getAddress(), c.getCourses()))
                .orElseThrow(() -> new MyCustomApiException("No Client with id "+id+" was found"));
    }

    //GET CLIENTS BY GENDER
    public List<ClientDTO> getClientsByGender(String text) {
        Gender gender = Gender.fromString(text);
        return convertData(repository.findByGender(gender));
    }

    // ADD NEW CLIENT
    public void addNewClient(Client c) {
        Optional<Client> clientOptional = repository.findByEmail(c.getEmail());
        if (clientOptional.isPresent()) {
            throw new MyCustomApiException("This email is already taken");
        }
        if (validateNumbers(c.getPhoneNumber().trim())) {
            repository.save(c);
        } else {
            throw new MyCustomApiException("phone number can only takes number values");
        }
    }

    //DELETE CLIENT BY ID
    public void deleteClient(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists) {
            throw new MyCustomApiException("this client id "+id+" does not exists");
        }
        repository.deleteById(id);
    }

    //UPDATE VALUES OF A CLIENT
    @Transactional
    public void updateClient(Long id, String name,
                             String lastName, String phoneNumber,
                             String email, String address) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new MyCustomApiException("No Client with id: "+id+" was found"));

        //Update first name
        if (name != null && !name.isEmpty() &&
                !Objects.equals(client.getName(), name)) {
            client.setName(name);
        }

        //Update last name
        if (lastName != null && !lastName.isEmpty() &&
                !Objects.equals(client.getLastName(), lastName)) {
            client.setLastName(lastName);
        }

        //Update phone number
        if (phoneNumber != null && !phoneNumber.isEmpty() &&
                !Objects.equals(client.getPhoneNumber(), phoneNumber)) {
            Optional<Client> clientOptional = repository.findByPhoneNumber(phoneNumber);
            if (clientOptional.isPresent()) {
                throw new MyCustomApiException("This phone number: "+phoneNumber+" is already taken");
            }

            if (validateNumbers(phoneNumber.trim())) {
                client.setPhoneNumber(phoneNumber);
            } else {
                throw new MyCustomApiException("phone number can only takes number values");
            }
        }

        //Update email
        if (email != null && !email.isEmpty() &&
                !Objects.equals(client.getEmail(), email)) {

            Optional<Client> clientOptional = repository.findByEmail(email);
            if (clientOptional.isPresent()){
                throw new MyCustomApiException("This email: "+email+" is already taken");
            }
            client.setEmail(email);
        }

        //Update address
        if (address != null && !address.isEmpty()) {
            client.setAddress(address);
        }
    }

    //Convert List of Client into List of ClientDto
    private List<ClientDTO> convertData(List<Client> clients) {
        return clients.stream()
                .map(c -> new ClientDTO(c.getId(), c.getName(), c.getLastName(),
                        c.getAge(), c.getBirthdate(),
                        c.getGender(), c.getPhoneNumber(),
                        c.getEmail(), c.getAddress(), c.getCourses()))
                .collect(Collectors.toList());
    }

    //Only takes numbers from a String values
    public boolean validateNumbers(String datos) {
        return datos.matches("[0-9]{10,15}");
    }
}