package com.panamby.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor	
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "Client")
public class Client {
	
	@DynamoDBHashKey
	private String name;
	
	@DynamoDBAttribute
	private String hasCard;
	
	@DynamoDBAttribute
	private String lastValue;
}