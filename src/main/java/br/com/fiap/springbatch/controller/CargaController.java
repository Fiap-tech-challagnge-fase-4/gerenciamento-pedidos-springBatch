package br.com.fiap.springbatch.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fiap.springbatch.model.Carga;
import br.com.fiap.springbatch.service.SalvarCarga;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/carga")
public class CargaController {

	private final SalvarCarga salvarCarga;
	
    public CargaController(SalvarCarga salvarCarga) {
        this.salvarCarga = salvarCarga;
    }

	@Operation(description = "Faz a carga do Arquivo")
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
