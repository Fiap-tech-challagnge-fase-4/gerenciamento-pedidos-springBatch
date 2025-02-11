package br.com.fiap.springBatch.gateway;

import br.com.fiap.springBatch.model.Carga;

public interface JobGateway {

	public void execute(Carga carga);

}
