#  Microsserviço de Importação de Massiva de Produtos

Este projeto faz parte do *Tech Challenge - Fase 4*, ele implementa a funcionalidade de carga de produtos utilizando Spring Batch, permitindo a importação automatizada de dados de produtos a partir de fontes externas, como arquivos CSV.

## Tecnologias Utilizadas

- **Java 17**: Versão de linguagem utilizada.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Batch**: Para implementar a funcionalidade de carga massiva de produtos.
- **Swagger**: Para documentação e testes das APIs.
- **JUnit, AssertJ e RestAssured**: Para criação de testes unitários e de integração.

## Para executar a aplicação via Docker, siga os comandos abaixo:

1. **Faça login no Docker:**
   ```bash
   docker login
    ```
2. **Execute o seguinte comando para subir os serviços:**
     ```bash
    docker compose up -d
    ```
## Instruções para Execução dos Testes

- Comando para execução dos **Testes Unitários**:
   ```bash
    mvn test
    ```
- Comando para execução dos **Testes Integrados**:
   ```bash
    mvn test -P integration-test
    ```

## Documentação da API

A documentação da API é gerada automaticamente pelo Swagger. Você pode acessá-la inserindo /swagger-ui/index.html ao final da url ou no seguinte endereço após iniciar a aplicação localmente:

URL LOCAL: [[http://localhost:8088/swagger-ui.html](http://localhost:8088/swagger-ui.html)]

Consulte a documentação do Swagger UI para ver todos os endpoints disponíveis e detalhes sobre cada um deles.
