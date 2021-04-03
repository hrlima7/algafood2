package br.com.roma.infraestructure.service;

public class ReportException extends RuntimeException {

	public ReportException() {
		super();

	}

	public ReportException(String menssagem, Throwable causa) {
		super(menssagem, causa);
		
	}

	public ReportException(String mensagem) {
		super(mensagem);
		
	}

	
}
