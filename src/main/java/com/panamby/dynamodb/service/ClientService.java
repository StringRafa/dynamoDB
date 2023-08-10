package com.panamby.dynamodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panamby.dynamodb.model.Client;
import com.panamby.dynamodb.model.exceptions.DynamoException;
import com.panamby.dynamodb.repository.ClientRepository;
import com.panamby.dynamodb.utils.TransactionTypeConstantUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
    public Client save(Client client) {

		log.info(String.format("Starting client registration in service. CLIENT [%s]", client));    	
    	
        clientRepository.save(client);

		log.info(String.format("Finished client registration in service. CLIENT [%s]", client));
        
        return client;
    }
    
    public Client getClientById(String clientId) {

		log.info(String.format("Starting find client in service. CLIENT_ID [%s]", clientId));
    	
    	Client clientResponse = clientRepository.getClientById(clientId);

		log.info(String.format("Client found finished in service. CLIENT [%s]", clientResponse)); 
        
        return clientResponse;
    }
    
    public String delete(String clientId) {

		log.info(String.format("Starting delete client in service. CLIENT_ID [%s]", clientId));
    	
    	String msg = clientRepository.delete(clientId);

		log.info(String.format("Finished delete client in service. CLIENT_ID [%s]", clientId)); 
    	
        return msg;
    }
    
	public Client update(String clientId, Client client) {

		log.info(String.format("Starting update client in service. CLIENT_ID [%s] - CLIENT_REQUEST [%s]", clientId, client));
		
		if(!clientId.equals(client.getName())) {
			
			throw new DynamoException("Body's id is different from URI's id", TransactionTypeConstantUtils.UPDATE_CLIENT_TRANSACTION);
		}		
		
		Client clientResponse = clientRepository.update(clientId, client);

		log.info(String.format("Finished delete client in repository. CLIENT_ID [%s] - CLIENT_RESPONSE [%s]", clientId, clientResponse)); 
		
	    return client;
	}
}
