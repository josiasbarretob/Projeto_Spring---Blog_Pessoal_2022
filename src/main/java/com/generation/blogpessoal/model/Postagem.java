package com.generation.blogpessoal.model;

import java.time.LocalDate; //Importação do LocalDate

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

@Entity //Classe que vai gerar uma tabela ou entidade no meu banco de dados
@Table (name = "tb_postagens") //Definir o nome da Tabela = CREATE TABLE.

public class Postagem {
	// Declaração dos atributos
	
	@Id  //Definir o ID como Chave Primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Definir como AutoIncremento do mySQL
	private	Long id;
	
	@NotBlank(message = "O Atributo título é obrigatório e náo pode ser vazio") // NotBlannk só pode ser usado com String
	@Size(min= 5 , max = 100 , message = "O atributo título deve contar no mínimo 5 e no máximo 100 caracteres")
	private String titulo;
	
	@NotNull(message = "O Atributo texto é obrigatório!")
	@Size(min= 10 , max = 1000 , message = "O atributo título deve contar no mínimo 10 e no máximo 1000 caracteres")
	private String texto;
	
	@UpdateTimestamp //Data automática do Servidor
	private LocalDate data;
	
	//Source > Generate Getters and Setters...
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}

