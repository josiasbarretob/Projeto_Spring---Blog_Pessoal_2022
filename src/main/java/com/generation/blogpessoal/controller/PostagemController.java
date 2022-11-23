package com.generation.blogpessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
//import java.util.list;
import antlr.collections.List;

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
}
