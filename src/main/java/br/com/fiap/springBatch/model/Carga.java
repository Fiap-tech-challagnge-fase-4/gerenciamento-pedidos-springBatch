package br.com.fiap.springBatch.model;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Carga {
	private String nome;
	//private String diretorio;

	private byte[] binario;
	private LocalDateTime dataHora;

	public Carga(String nome, byte[] binario) {
		this.nome = nome;
		//this.diretorio = diretorio;
		this.binario = binario;
		this.dataHora = LocalDateTime.now();
	}
}
