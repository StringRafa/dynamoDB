package com.panamby.dynamodb.repository;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.panamby.dynamodb.model.Client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class ClientRepository {
	
    private DynamoDBMapper dynamoDBMapper;
    
    public Client save(Client client) {

		log.info(String.format("Starting client registration CLIENT [%s]", client));
    	
        dynamoDBMapper.save(client);

		log.info(String.format("Finished client registration CLIENT [%s]", client));
        
        return client;
    }
    
    public Client getFuncionarioById(String clientId) {

		log.info(String.format("Starting find client. CLIENT_ID [%s]", clientId));

		Client clientResponse = dynamoDBMapper.load(Client.class, clientId);

		log.info(String.format("Client found. CLIENT [%s]", clientResponse));    	
    	
        return clientResponse;
    }
    
    public String delete(String clientId) {
        Client client = dynamoDBMapper.load(Client.class, clientId);
        dynamoDBMapper.delete(client);
        return "Cliente Deletado!!";
    }
    
	public Client update(String clientId, Client client) {
		
	    dynamoDBMapper.save(client,
	            new DynamoDBSaveExpression().withExpectedEntry("name",
	                    new ExpectedAttributeValue(new AttributeValue().withS(clientId))));
	    return client;
	}

}
