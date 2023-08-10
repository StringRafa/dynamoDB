package com.panamby.dynamodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panamby.dynamodb.model.Client;
import com.panamby.dynamodb.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/client/v1")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@PostMapping()
	public ResponseEntity<Client> save(@RequestBody Client client) {

		log.info(String.format("Starting client registration CLIENT_ID[%s] - HAS_CARD [%s]", client.getName(),
				client.getHasCard()));

		Client clientResponse = service.save(client);

		log.info(String.format("Finished client registration. CLIENT [%s]", clientResponse));

		return new ResponseEntity<Client>(clientResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> find(@PathVariable("id") String clientId) {

		log.info(String.format("Starting find client. CLIENT_ID [%s]", clientId));

		Client clientResponse = service.getClientById(clientId);

		log.info(String.format("Client found. CLIENT [%s]", clientResponse));

		return new ResponseEntity<Client>(clientResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String clientId) {

		log.info(String.format("Starting delete client. CLIENT_ID [%s]", clientId));

		String clientResponse = service.delete(clientId);

		log.info(String.format(clientResponse.concat(" CLIENT_ID [%s]"), clientId));

		return new ResponseEntity<String>(clientResponse, HttpStatus.CREATED);
	}
    
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") String clientId, @RequestBody Client client) {

		log.info(String.format("Starting update client. CLIENT_ID [%s] - BODY [%s]", clientId, client));
    	
    	Client clientResponse = service.update(clientId, client);

		log.info(String.format("Finished update client. CLIENT_RESPONSE [%s]", clientResponse));
        
        return new ResponseEntity<Client>(clientResponse, HttpStatus.CREATED);
    }
}
