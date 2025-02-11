package br.com.fiap.springBatch.service.impl;

import org.springframework.stereotype.Service;

import br.com.fiap.springBatch.gateway.JobGateway;
import br.com.fiap.springBatch.model.Carga;
import br.com.fiap.springBatch.service.SalvarCarga;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalvarCargaImpl implements SalvarCarga {

	private final JobGateway jobGateway;
	
	@Override
	public void salvar(Carga carga) {
		jobGateway.execute(carga);
	}
}
