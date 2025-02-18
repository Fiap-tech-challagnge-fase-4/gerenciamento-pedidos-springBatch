package br.com.fiap.springbatch.model;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
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
