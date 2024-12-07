package com.newproject.Mondongo.controllers;

import com.newproject.Mondongo.dto.ClientDTO;
import com.newproject.Mondongo.entities.Client;
import com.newproject.Mondongo.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inicio")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    //GET ALL
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping(path = "/clients")
    public List<ClientDTO> getClients() {
        return service.getClients();
    }

    //GET BY ID
    @GetMapping(path = "clients/{id}")
    public ClientDTO getClientById(@PathVariable("id") Long id){
        return service.getClientById(id);
    }

    //GET BY GENDER
    @GetMapping(path = "clients/gender/{text}")
    public List<ClientDTO> getClientByGender(@PathVariable String text) {
        return service.getClientsByGender(text);
    }

    //POST
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(path = "/clients/api")
    public void addNewClient(@RequestBody Client c) {
        service.addNewClient(c);
    }

    //DELETE
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping(path = "clients/delete/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        service.deleteClient(id);
    }

    //PUT
    @PutMapping(path = "/clients/update/{id}")
    public void updateClient(@PathVariable("id") Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String phoneNumber,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String address) {
        service.updateClient(id,name,lastName,phoneNumber,email,address);
    }
}