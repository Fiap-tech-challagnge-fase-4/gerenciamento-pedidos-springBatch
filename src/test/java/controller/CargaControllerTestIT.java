package controller;

import br.com.fiap.springBatch.SpringBatchApplication;
import br.com.fiap.springBatch.model.Carga;
import br.com.fiap.springBatch.service.SalvarCarga;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@AutoConfigureTestDatabase
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = SpringBatchApplication.class)
public class CargaControllerTestIT {

    @LocalServerPort
    private int port;

    @Autowired
    private SalvarCarga salvarCarga;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void deveCarregarArquivoCorretamente() throws Exception {
        // Arrange
        File arquivo = new File("src/test/resources/teste_produtos.csv"); // ou crie um arquivo temporário para os testes
        MultipartFile multipartFile = new MockMultipartFile(
                "file", // nome do parâmetro no método controller
                "produtos.csv", // nome do arquivo
                "text/csv", // tipo de mídia
                new FileInputStream(arquivo) // entrada do arquivo
        );

        // Act & Assert
        given().filter(new AllureRestAssured())
                .multiPart("file", multipartFile.getOriginalFilename(), multipartFile.getInputStream(), MediaType.MULTIPART_FORM_DATA_VALUE)
                .when().post("/api/carga/importar")
                .then().statusCode(HttpStatus.OK.value());
    }

}
