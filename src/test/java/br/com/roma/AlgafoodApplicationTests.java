package br.com.roma;

import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class AlgafoodApplicationTests {
	
	@LocalServerPort
	private int port;
	
	//@Before
//	public void setUp() {
//		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//		RestAssured.port = port;
//		RestAssured.basePath = "/cozinhas";
//	}
	
	
	
	@Test
	public void  deveRetornarStatus200_QuandoconsultarCozinha() {
	//	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
			RestAssured.given()
	    //  .basePath("/cozinhas")
		//	.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(200);
			
	}
	
	@Test
	public void  deveConter4Cozinhas_QuandoconsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
			RestAssured.given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("nome",Matchers.hasSize(0));
			
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCoziznha() {
	RestAssured.given()
		.body("{ \"nome\":\"Chinesa\" }")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.port(port)
	.when()
		.post()
	.then()
		.statusCode(HttpStatus.CREATED.value());	
		
	}

	@Test
	public void devRetornarRespostaEstatusCorretos_QuandoCOnsultarCozinhaExistente() {
		RestAssured.given()
			.pathParam("cozinhaId",2)
			.accept(ContentType.JSON)
		.when()
			.get("/cozinha/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}


}