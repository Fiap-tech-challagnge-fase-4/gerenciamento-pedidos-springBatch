package br.com.fiap.springbatch.model;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Carga {
	private String nome;

	private byte[] binario;
	private LocalDateTime dataHora;

	public Carga(String nome, byte[] binario) {
		this.nome = nome;
		this.binario = binario;
		this.dataHora = LocalDateTime.now();
	}
}
