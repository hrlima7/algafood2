package br.com.roma.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.roma.domain.exception.EntidadeEmUsoException;
import br.com.roma.domain.exception.EntidadeNaoEncontradaException;
import br.com.roma.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler  {
	
	@Autowired
	private MessageSource messageSource;
	
	 @ExceptionHandler(EntidadeNaoEncontradaException.class)
	  public ResponseEntity<?> handl1eEntidadeNaoEncontradoException(
			  EntidadeNaoEncontradaException ex,WebRequest request){

		 HttpStatus status = HttpStatus.NOT_FOUND;
		 String detail = ex.getMessage();
		 
		 Problema problema = Problema.builder()
				 .status(status.value())
				 .type("https://roma.com.br/entidade-nao-encontrada")
				 .title("Entidade não encontrada")
				 .detail(ex.getMessage())
				 .build();
		 
		 
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).body
				  (problema);
	  }
	 
	  @ExceptionHandler(NegocioException.class)
	  public ResponseEntity<?> tratarNegocioException(NegocioException e){	    
		  Problema problema =  Problema.builder()
				  .dataHora(LocalDateTime.now())
				  .userMensagem(e.getMessage()).build();
		  
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).
				  body(e.getMessage());
	  }
	  
	  @ExceptionHandler(EntidadeEmUsoException.class)
	  public ResponseEntity<?> tratarConflict(EntidadeEmUsoException e){		 
		  Problema problema = Problema.builder()
				  .dataHora(LocalDateTime.now())
				  .userMensagem(e.getMessage()).build();
		  return ResponseEntity.status(HttpStatus.CONFLICT)
				  .body(problema);
	  }
	  
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(body == null) {
		body = Problema.builder()
				.title(status.getReasonPhrase())
				.status(status.value())
				.build();
		}else if(body instanceof String){
			body = Problema.builder()
					.title((String) body)
					.status(status.value())
					.build();	
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			 HttpHeaders headers, HttpStatus status, WebRequest request){
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String detail = "Um ou mais campos estão invalidos. Faça o preenchimento correto";
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<Problema.Field> problemaFields = bindingResult.getFieldErrors().stream()
				.map(fieldError -> { 
					String message =  messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
					
					return Problema.Field.builder()
						.name(fieldError.getField())
						.userMessage(message)
						.build();
				})
				.collect(Collectors.toList());
		
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.fields(problemaFields)
				.title(problemType.getTitle())
				.detail(detail)
				.build();
				
			return handleExceptionInternal(ex,problema, headers,status,request);
		
		
		
		
	}
	
	
 
	  
}


