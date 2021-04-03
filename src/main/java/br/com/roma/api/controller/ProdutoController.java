package br.com.roma.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.model.Produto;
import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.repository.ProdutoRepository;
import br.com.roma.domain.model.service.ProdutoService;
import br.com.roma.domain.model.service.RestauranteCadastroService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class ProdutoController {
	
		@Autowired
		ProdutoService produtoService;
		@Autowired
		RestauranteCadastroService restauranteService;

		@Autowired
		ProdutoRepository produtoRepository;
		
		
		@GetMapping
		public List<Produto> listar(@PathVariable Long restauranteId,
					@RequestParam(required = false) boolean incluirInativos){
			
			Restaurante restaurante =  restauranteService.buscarOuFalhar(restauranteId);
			List<Produto> todosProdutos = null;
			
			if(incluirInativos) {
				todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
				
			}else {
				 todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
			}
			
			
			return todosProdutos;
		}
		
		@GetMapping("/{produtoId}")
		public Produto listarProdutos(@PathVariable Long produtoId){
			return produtoService.buscarOuFalhar(produtoId);
		}
		
		@PostMapping
		public Produto salva(@RequestBody Produto produto, 
				@PathVariable Long restauranteId) {
				System.out.println("O ID DO RESTAURANTE É:"+ restauranteId);
			
				Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
				produto.setRestaurante(restaurante);
			
				return produtoService.salvar(produto);
		}

		@DeleteMapping("/{produtoId}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deletar (@PathVariable Long produtoId, 
				@PathVariable Long restauranteId) {
				System.out.println("O ID DO RESTAURANTE É:"+ restauranteId);
				Produto produto = produtoService.buscarOuFalhar(produtoId);
					
				if(restauranteId.equals(produto.getRestaurante().getId())) {			
				 produtoService.deletar(produto);
				}
				
		}
		
		//@PutMapping("/{produtoId}")
		public Produto atualizar(@PathVariable Long produtoId,@PathVariable Long restauranteId,	
				@RequestBody Produto produto) {
						
			System.out.println("VAI COMECAR"+ produtoId);
			Produto	produtoAtual = produtoService.buscarOuFalhar(produtoId);
		
		System.out.println("ACHOU O PRODUTO"+ produtoAtual.toString());
		
		if(restauranteId.equals(produto.getRestaurante().getId())) {
			
			System.out.println("ENTROU NO SE: "+ produtoAtual.toString());
				BeanUtils.copyProperties(produto, produtoAtual, "id");
				produtoService.salvar(produtoAtual);
			}else {
				System.out.println("deu merda");
			}

			return produtoAtual;
		}
		
		 @PutMapping("/{produtoId}")
		    public Produto atualizarNovo(@PathVariable Long restauranteId, @PathVariable Long produtoId,
		            @RequestBody  Produto produto) {
		        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);
		        
		        	BeanUtils.copyProperties(produto, produtoAtual, "id");
		        
		    //    produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
		        
		       return produtoAtual = produtoService.salvar(produtoAtual);
		   
		    }   
		
	
		
		
}
