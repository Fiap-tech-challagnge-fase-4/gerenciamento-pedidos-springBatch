package br.com.fiap.springBatch.model;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Produto {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidadeestoque;
    private String categoria;
    private String imagemurl;
    private String codigobarras;
    private String status;
}
