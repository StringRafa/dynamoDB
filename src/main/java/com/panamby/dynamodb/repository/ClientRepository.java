package com.panamby.dynamodb.repository;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.panamby.dynamodb.model.Client;
import com.panamby.dynamodb.model.exceptions.DynamoException;
import com.panamby.dynamodb.utils.TransactionTypeConstantUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class ClientRepository {
	
    private static final String ATTRIBUTE_NAME = "name";
	private static final String CLIENT_DELETE = "Cliente Deletado!!";
	
	private DynamoDBMapper dynamoDBMapper;
    
    public Client save(Client client) {

		log.info(String.format("Starting client registration CLIENT [%s]", client));
    	
		try {

			dynamoDBMapper.save(client);
		} catch (ResourceNotFoundException e) {

			throw new DynamoException(e.getErrorMessage(), TransactionTypeConstantUtils.SAVE_CLIENT_TRANSACTION);
		}

		log.info(String.format("Finished client registration CLIENT [%s]", client));
        
        return client;
    }
    
    public Client getFuncionarioById(String clientId) {

		log.info(String.format("Starting find client. CLIENT_ID [%s]", clientId));

		Client clientResponse= null;
		
		try {
			
			clientResponse = dynamoDBMapper.load(Client.class, clientId);
		} catch (ResourceNotFoundException e) {
			
			throw new DynamoException(e.getErrorMessage(), TransactionTypeConstantUtils.GET_CLIENT_TRANSACTION);
		}

		log.info(String.format("Client found. CLIENT [%s]", clientResponse));    	
    	
        return clientResponse;
    }
    
    public String delete(String clientId) {

		log.info(String.format("Starting delete client. CLIENT_ID [%s]", clientId));
    	
        try {
        	
			Client client = dynamoDBMapper.load(Client.class, clientId);			
			dynamoDBMapper.delete(client);
		} catch (ResourceNotFoundException e) {
			
			throw new DynamoException(e.getErrorMessage(), TransactionTypeConstantUtils.DELETE_CLIENT_TRANSACTION);
		}

		log.info(String.format("Finished delete client . CLIENT_ID [%s]", clientId));  
        
        return CLIENT_DELETE;
    }
    
	public Client update(String clientId, Client client) {

		log.info(String.format("Starting update client. CLIENT_ID [%s] - CLIENT_REQUEST [%s]", clientId, client));
		
		try {

			dynamoDBMapper.save(client, new DynamoDBSaveExpression().withExpectedEntry(ATTRIBUTE_NAME,
					new ExpectedAttributeValue(new AttributeValue().withS(clientId))));
		} catch (ResourceNotFoundException e) {

			throw new DynamoException(e.getErrorMessage(), TransactionTypeConstantUtils.UPDATE_CLIENT_TRANSACTION);
		}

		log.info(String.format("Finished delete client . CLIENT_ID [%s]", clientId));  
		
		return client;
	}

}
