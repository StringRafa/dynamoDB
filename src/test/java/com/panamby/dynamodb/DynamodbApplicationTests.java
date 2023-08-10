package com.panamby.dynamodb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.panamby.dynamodb.model.Client;
import com.panamby.dynamodb.service.ClientService;

@SpringBootTest
class DynamodbApplicationTests {

	  @Autowired
	  private ClientService customerService;

	  @Test
	  void testCreateCustomer() {
	    Client client = new Client(); 
	    client.setName("ClientId");
	    client.setHasCard("1");
	    client.setLastValue("30");
	    customerService.save(client);
	  }

}
