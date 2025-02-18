package impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.springbatch.gateway.JobGateway;
import br.com.fiap.springbatch.model.Carga;
import br.com.fiap.springbatch.service.impl.SalvarCargaImpl;


@ExtendWith(MockitoExtension.class)
class SalvarCargaImplTest {

    @Mock
    private JobGateway jobGateway;

    @InjectMocks
    private SalvarCargaImpl salvarCargaImpl;

    @Test
    void testSalvarCarga() {
        // Arrange
        Carga carga = new Carga("produtos.csv", new byte[10]);

        // Act
        salvarCargaImpl.salvar(carga);

        // Assert
        verify(jobGateway, times(1)).execute(carga);
    }
}
