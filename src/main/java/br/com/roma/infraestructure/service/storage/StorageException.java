package br.com.roma.infraestructure.service.storage;

public class StorageException extends RuntimeException {

	public StorageException(String message, Throwable causa, boolean arg2, boolean arg3) {
		super(message, causa, arg2, arg3);

	}

	public StorageException(String message, Throwable causa) {
		super(message, causa);
		
	}

	
	
}
