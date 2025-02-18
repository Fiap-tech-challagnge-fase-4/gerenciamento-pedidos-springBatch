package br.com.fiap.springbatch.exception;

public class ErroAoProcessarJobException extends RuntimeException {
    // Construtor com mensagem personalizada
    public ErroAoProcessarJobException(String mensagem) {
        super(mensagem);
    }
}
