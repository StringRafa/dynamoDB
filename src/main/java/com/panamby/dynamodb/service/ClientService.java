package com.panamby.dynamodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panamby.dynamodb.model.Client;
import com.panamby.dynamodb.model.exceptions.DynamoException;
import com.panamby.dynamodb.repository.ClientRepository;
import com.panamby.dynamodb.utils.TransactionTypeConstantUtils;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
    public Client save(Client client) {
        clientRepository.save(client);
        return client;
    }
    
    public Client getFuncionarioById(String clientId) {
        return clientRepository.getFuncionarioById(clientId);
    }
    
    public String delete(String clientId) {
        clientRepository.delete(clientId);
        return "Cliente Deletado!!";
    }
    
	public Client update(String clientId, Client client) {
		
		if(!clientId.equals(client.getName())) {
			
			throw new DynamoException("Body's id is different from URI's id", TransactionTypeConstantUtils.UPDATE_TRANSACTION);
		}		
		
	    clientRepository.update(clientId, client);
	    return client;
	}
}
