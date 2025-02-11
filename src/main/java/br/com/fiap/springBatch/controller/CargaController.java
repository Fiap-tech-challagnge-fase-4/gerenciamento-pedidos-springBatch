package br.com.fiap.springBatch.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fiap.springBatch.model.Carga;
import br.com.fiap.springBatch.service.SalvarCarga;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/carga")
public class CargaController {

	private final SalvarCarga salvarCarga;
	
    public CargaController(SalvarCarga salvarCarga) {
        this.salvarCarga = salvarCarga;
    }
	
    //@Value("${carga.input-path}")
    //private String diretorio;
    
    @PostMapping("/importar")
	public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile file) {

    	try {
    		Carga carga = new Carga("produtos.csv", file.getBytes());
    		salvarCarga.salvar(carga);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
