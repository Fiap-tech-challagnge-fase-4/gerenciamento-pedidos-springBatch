package impl;

import br.com.fiap.springBatch.model.Carga;
import br.com.fiap.springBatch.gateway.JobGateway;
import br.com.fiap.springBatch.service.SalvarCarga;
import br.com.fiap.springBatch.service.impl.SalvarCargaImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class SalvarCargaImplTest {

    @Mock
    private JobGateway jobGateway;

    @InjectMocks
    private SalvarCargaImpl salvarCargaImpl;

    @Test
    public void testSalvarCarga() {
        // Arrange
        Carga carga = new Carga("produtos.csv", new byte[10]);

        // Act
        salvarCargaImpl.salvar(carga);

        // Assert
        verify(jobGateway, times(1)).execute(carga);
    }
}
