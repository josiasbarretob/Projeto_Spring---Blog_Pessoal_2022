package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping ("/postagens")
@CrossOrigin (origins = "*", allowedHeaders = "*") //Aceita requisição de qualquer servidor.
public class PostagemController {

	@Autowired //Injeção de Dependências
	private PostagemRepository postagemRepository;
	
	@GetMapping //Consulta
	public ResponseEntity<java.util.List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		// SELECT * FROM tb_postagens;
		
	}//Resposta HTTP
	
	/* http://localhost:8080/postagens/1 */
	@GetMapping("/{id}") //Valor é uma variável e náo um caminho.
	public ResponseEntity <Postagem> getById(@PathVariable Long id){
		/* Optional <Postagem> buscaPostagem = postagemRepository.findById(id);
		
		if (buscaPostagem.isPresent())
			return ResponseEntity.ok (buscaPostagem.get());
		else
			return ResponseEntity.notFound().build();
		*/
		return postagemRepository.findById(id)
				.map (resposta -> ResponseEntity.ok(resposta))
				.orElse (ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getAll(@PathVariable String titulo){
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }
	
	@PostMapping
	public ResponseEntity <Postagem> postPostagem(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity <Postagem> putPostagem(@Valid @RequestBody Postagem postagem){
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok().body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostagem(@PathVariable Long id) {
        try {
            postagemRepository.deleteById(id);
            return ResponseEntity.status(204).build();

        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
		}
	
}
