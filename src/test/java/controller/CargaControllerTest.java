package controller;

import br.com.fiap.springBatch.controller.CargaController;
import br.com.fiap.springBatch.model.Carga;
import br.com.fiap.springBatch.service.SalvarCarga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CargaControllerTest {

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
    public void testHandleFileUpload_Success() throws Exception {

        byte[] fileContent = "Conteúdo do arquivo".getBytes();
        when(file.getBytes()).thenReturn(fileContent);
        when(file.getOriginalFilename()).thenReturn("produtos.csv");


        ResponseEntity<Void> response = cargaController.handleFileUpload(file);


        verify(salvarCarga, times(1)).salvar(any(Carga.class));


        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testHandleFileUpload_IOException() throws Exception {

        when(file.getBytes()).thenThrow(new IOException("Erro ao ler o arquivo"));


        ResponseEntity<Void> response = cargaController.handleFileUpload(file);


        verify(salvarCarga, times(0)).salvar(any(Carga.class));


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
