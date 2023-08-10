package com.panamby.dynamodb.model.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class DynamoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private String transactionType;

	public DynamoException(String message) {

		super(message);

		this.message = message;
	}

	public DynamoException(String message, String transactionType) {
		super(message);
		this.message = message;
		this.transactionType = transactionType;
	}

}
