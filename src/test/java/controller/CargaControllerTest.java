package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import br.com.fiap.springbatch.controller.CargaController;
import br.com.fiap.springbatch.model.Carga;
import br.com.fiap.springbatch.service.SalvarCarga;

class CargaControllerTest {

    @Mock
    private SalvarCarga salvarCarga;


    @InjectMocks
    private CargaController cargaController;

    @Mock
    private MultipartFile file;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleFileUpload_Success() throws Exception {

        byte[] fileContent = "Conteúdo do arquivo".getBytes();
        when(file.getBytes()).thenReturn(fileContent);
        when(file.getOriginalFilename()).thenReturn("produtos.csv");


        ResponseEntity<Void> response = cargaController.handleFileUpload(file);


        verify(salvarCarga, times(1)).salvar(any(Carga.class));


        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testHandleFileUpload_IOException() throws Exception {

        when(file.getBytes()).thenThrow(new IOException("Erro ao ler o arquivo"));


        ResponseEntity<Void> response = cargaController.handleFileUpload(file);


        verify(salvarCarga, times(0)).salvar(any(Carga.class));


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
