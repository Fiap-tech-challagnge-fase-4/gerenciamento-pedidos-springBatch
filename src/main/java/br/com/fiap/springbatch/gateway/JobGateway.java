package br.com.fiap.springbatch.gateway;

import br.com.fiap.springbatch.model.Carga;

public interface JobGateway {

	public void execute(Carga carga);

}
